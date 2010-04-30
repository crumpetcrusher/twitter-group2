//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : SubscriptionsManager
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class is the manager for dealing with any subscription added to the program. 
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

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

import Changes.Search;
import Changes.SubscriptionItem;
import GUI.RootGUI;
import GUI.SubscriptionItemViewer;
import GUI.SubscriptionsViewer;
import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;
import Twitter.Tweeter;

public class SubscriptionsManager implements ProgramStateListener {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //


    private ArrayList<SubscriptionItem> _subscriptions = new ArrayList<SubscriptionItem>();
    private String                      subscriptionListLocation;
    private SubscriptionsViewer         subscriptionVwr;
    private ButtonManager               buttonMgr;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //

    /**
     * Constructor for creating a new ArrayList for holding our tweeters
     * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
     * 
     * @param Location
     * @author Scott Smiesko
     */
    public SubscriptionsManager(String Location, ButtonManager buttonMgrIn, RootGUI gui) {
        
        // Set attributes with passed in objects
        //
        buttonMgr = buttonMgrIn;
        subscriptionVwr = new SubscriptionsViewer(this, buttonMgr);
        gui.add(subscriptionVwr, BorderLayout.WEST);
        subscriptionListLocation = Location;
        // Document subscriptionList = null;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //

    // Fills the subscribedTweeters list with the subscriptionList document
    // 
    private void initializeTweeters(Document subscriptionList) {

        // First, we fill our tweeters
        //
        Element subscriptions;

        NodeList subscripts;
        Element subscript;
        Element search;
        String text;
        boolean isSearch;

        _subscriptions = new ArrayList<SubscriptionItem>();

        subscriptions = (Element) (subscriptionList.getDocumentElement());

        subscripts = subscriptions.getElementsByTagName("name");

        // Parse all subscriptions found in the XML
        //
        for (int t = 0; t < subscripts.getLength(); ++t) {
            subscript = (Element) (subscripts.item(t));
            isSearch = Boolean.parseBoolean(subscript.getAttribute("Search"));
            text = subscript.getTextContent();
            if (!isSearch)
                // Add Tweeter
                //
                addTweeterSubscription(text);
            else
                // Add Search
                //
                addSubscription(new Search(text));
        }
        System.out.println("Array of tweeters is now constructed..");

    }

    // Removes user from the subscription list (removes from ArrayList and saves to XML)
    //
    public void removeSubscription(SubscriptionItem item) {
        
        // What if our subscriptionList is not loaded?
        //
        if (subscriptionListLocation == null)
            throw new NullPointerException(
                    "Subscription list location was not initialized!");

        // Check item we want to delete with all items in the subscriptionsList to make sure it's there
        //
        for (SubscriptionItem subscriptItem : _subscriptions)
            if (subscriptItem.equals(item)) {
                _subscriptions.remove(subscriptItem);
                try {
                    
                    // Commit changes
                    //
                    writeDocument();
                    System.out.println("Subscription Removed");
                }
                catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (TransformerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                // Break if we found one
                //
                break;
            }
        
        // Refresh our subscriptionsViewer with the updated information
        //
        subscriptionVwr.refresh();
    }

    // Adds new user to the subscription list (adds to ArrayList and saves to XML)
    //
    public void addTweeterSubscription(String name) {
        Tweeter tweeter = new Tweeter(name);
        tweeter.addProgramStateListener(this);
    }

    // Add subscription to our array list and refresh the subscriptionsViewer for updated info.
    // 
    public void addSubscription(SubscriptionItem item) {
        _subscriptions.add(item);
        subscriptionVwr.refresh();
    }
    
    // Processes the XML and readies it for a commit to file
    //
    public void writeDocument() throws ParserConfigurationException,
            TransformerException {
        if (subscriptionListLocation == null) {
            throw new NullPointerException(
                    "Subscription list was not initialized!");
        }

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document newSubscriptions = docBuilder.newDocument();

        Element root = newSubscriptions.createElement("Subscriptions");
        newSubscriptions.appendChild(root);

        for (SubscriptionItem subscriptItem : _subscriptions) {
            Element child = newSubscriptions.createElement("name");
            root.appendChild(child);
            child.setAttribute("Search", Boolean.toString(subscriptItem
                    .isSearch()));

            Text text = newSubscriptions.createTextNode(subscriptItem.text());
            child.appendChild(text);
        }

        commitSubscriptions(newSubscriptions);

    }

    // Physically commits the information from WriteDocument() to a XML file.
    //
    private void commitSubscriptions(Document newSubscriptions)
            throws TransformerException {

        DOMSource source = new DOMSource(newSubscriptions);

        File file = new File(subscriptionListLocation);
        Result result = new StreamResult(file);

        Transformer xformer = TransformerFactory.newInstance().newTransformer();

        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");

        xformer.transform(source, result);

    }

    // Returns all subscriptions in the ArrayList
    //
    public ArrayList<SubscriptionItem> getSubscriptions() {
        return _subscriptions;
    }

    // Method to get all subscriptionItemViewers that are currently selected for use 
    // in a composite timeline
    //
    public SubscriptionItemViewer[] getSelected() {
        return subscriptionVwr.getSelected();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Threading
    //
    
    // Method to tell SubscriptionsManager an executed event has been received
    //
    @Override
    public void stateReceived(ProgramStateEvent event) {
        System.out.println("State Received: " + event.state());
        if (event.state() == ProgramState.SUBSCRIPTION_ADDED) {
            System.out.println("Add Subscription");
            addSubscription((SubscriptionItem) event.getSource());
        }
    }

    // Method to tell all listeners of SubscriptionsManager that a subscription has been added
    //
    private synchronized void subscriptionAdded() {
        subscriptionVwr.refresh();
        // subscriptVwer.refresh(this, buttonMgr);
    }
}