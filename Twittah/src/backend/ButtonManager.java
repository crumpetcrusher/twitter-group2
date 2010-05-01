//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Class Name           : ButtonManager
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This class handles all requests to view/update/delete/parse xml/write xml/search/sort/and shutdown. 
// The RootGUI object is passed to it upon creation, which is passed along to SubscriptionsManager and
// TimelinesManager to be able to handle all above-mentioned requests. An init() function is passed a document
// containing the settings for the program that are saved when the program last exits.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import Changes.OrganizeType;
import Changes.Search;
import Changes.SubscriptionItem;
import GUI.RootGUI;
import GUI.SubscriptionItemViewer;

public class ButtonManager {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    // This class has 3 attributes used to manage all the requests
    // 
    // subscriptionsMgr         :  The class that handles all of our subscription items.
    //
    // timelinesMgr             :  The class that handles all of the information regarding timelines
    //
    // settingsLocation         :  The location of our settings.xml file that stores all the users settings from
    //                             the last time the program was ran.
    //
    //
    private SubscriptionsManager subscriptionsMgr;
    private TimelinesManager     timelinesMgr;
    private String               settingsLocation = "src/settings.xml";

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // This is the constructor for the class.  It takes in our root GUI to pass along to subscriptionsManager
    // and timelinesManager so they can manipulate the GUI when called to do so through this class.
    //
    public ButtonManager(RootGUI gui) {
        subscriptionsMgr = new SubscriptionsManager(settingsLocation, this, gui);
        timelinesMgr     = new TimelinesManager(subscriptionsMgr, gui);
        init(XMLHelper.getDocumentByLocation(settingsLocation));
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    
    //
    // This is the initialize method that parses the settings.xml file passed to it from our constructor
    //  If there are no settings, then the program runs as if it was first loaded.
    //
    private void init(Document doc) {
        if (doc != null) {
            
            Element subscriptions;
            NodeList subscripts;
            Element subscript;
            String text;
            boolean isSearch;

            // Get subscriptions from our XML files and parse for information.
            //
            subscriptions = (Element) (doc.getDocumentElement());
            timelinesMgr.organize(OrganizeType.valueOf(subscriptions.getAttribute("Sort")));

            subscripts = subscriptions.getElementsByTagName("name");

            // Iterate through the subscriptions parsed earlier and create the users based on the last program load.
            //
            for (int t = 0; t < subscripts.getLength(); ++t) {
                subscript = (Element) (subscripts.item(t));
                isSearch = Boolean.parseBoolean(subscript.getAttribute("Search"));
                text = subscript.getTextContent();
                if (!isSearch)
                    subscriptionsMgr.addTweeterSubscription(text);
                else
                    subscriptionsMgr.addSubscription(new Search(text));
            }
        }
    }
    
    // This will toggle the automatic refresh thread for the composite timeline in timelineMgr
    //
    public void toggleAutomaticRefresh()
    {
        timelinesMgr.toggleAutomaticRefresh();
    }

    // Add a subscription passed in by name to our subscriptionsManager when the user selects add subscription
    //
    public void doAddSubscriptionTweeter(String newName) {
        subscriptionsMgr.addTweeterSubscription(newName);
    }

    // Add a display subscription to the timeline
    //
    public void addDisplaySubscription(SubscriptionItem item) {
        timelinesMgr.addToTimeline(item);
    }

    // Delete a subscription from the subscriptionsManager when the user selects the Delete button on a user
    //
    public void doDeleteSubscription(SubscriptionItem item) {
        subscriptionsMgr.removeSubscription(item);
    }

    // Refresh the timeline to show the newest information when a user selects the Refresh button
    //
    public void doRefreshTimeline() {
        timelinesMgr.refreshTimeline();
    }

    // Select a subscription to be shown in the TimelineViewer when a user selects the view button
    //
    public void doSelectSubscription(SubscriptionItem item) {
        timelinesMgr.clearTimeline();
        timelinesMgr.addToTimeline(item);
    }

    // Show a composite timeline of the subscriptionItems currently selected in the SubscriptionsViewer
    //
    public void doShowCompositeTimeline() {
        timelinesMgr.clearTimeline();
        for (SubscriptionItemViewer item : subscriptionsMgr.getSelected())
            timelinesMgr.addTimeline(item.timeline());
    }

    // Toggle the type of sorting we will be using when a user selects the sorting style in the menubar
    //
    public void toggle(OrganizeType type) {
        OrganizeType current = timelinesMgr.organizeType();
        if (type == OrganizeType.A_Z || type == OrganizeType.Z_A)
            type = (current == OrganizeType.A_Z ? OrganizeType.Z_A : OrganizeType.A_Z);
        else
            type = (current == OrganizeType.JAN_DEC ? OrganizeType.DEC_JAN : OrganizeType.JAN_DEC);

        timelinesMgr.organize(type);
    }

    // Search for a query and add it to the program as a subscription for later viewing when the user
    // selects the Search button
    //
    public void doSearch(String query) {
        timelinesMgr.clearTimeline();
        Search newSearch = new Search(query);
        subscriptionsMgr.addSubscription(newSearch);
        timelinesMgr.addToTimeline(newSearch);
    }

    // Delete previous timelines in the view and save timelines currently in view for the next program load
    //
    public void systemShutdown() {
        timelinesMgr.deletePreviousTimelines();
        timelinesMgr.saveTimelines();
        try {
            writeDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    // Write the org.w3c.dom.Document object of subscripitons to pass into commitSubscriptions() for writing to file
    // 
    private void writeDocument() throws ParserConfigurationException,
            TransformerException {

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document newSubscriptions = docBuilder.newDocument();

        Element root = newSubscriptions.createElement("Subscriptions");
        root.setAttribute("Sort", timelinesMgr.organizeType().toString());
        newSubscriptions.appendChild(root);

        for (SubscriptionItem subscriptItem : subscriptionsMgr
                .getSubscriptions()) {
            Element child = newSubscriptions.createElement("name");
            root.appendChild(child);
            child.setAttribute("Search", Boolean.toString(subscriptItem
                    .isSearch()));

            Text text = newSubscriptions.createTextNode(subscriptItem.text());
            child.appendChild(text);
        }
        
        commitSubscriptions(newSubscriptions);  
    }

    // Commit the subscriptions Document into a file
    //
    private void commitSubscriptions(Document newSubscriptions) throws TransformerException {

        DOMSource source = new DOMSource(newSubscriptions);

        File file = new File("src/settings.xml");
        Result result = new StreamResult(file);

        Transformer xformer = TransformerFactory.newInstance().newTransformer();

        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");

        xformer.transform(source, result);
    }
}
