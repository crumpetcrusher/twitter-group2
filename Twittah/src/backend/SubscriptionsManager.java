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

    /**
     * Stores the arraylist of Tweeters (people we are subscribing to)
     */
    private ArrayList<SubscriptionItem> _subscriptions = new ArrayList<SubscriptionItem>();

    /**
     * Stores the file location of the subscription list.
     */
    private String                      subscriptionListLocation;
    SubscriptionsViewer                 subscriptionVwr;
    // SubscriptViewer subscriptVwer;
    private ButtonManager               buttonMgr;

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //

    /**
     * Constructor for creating a new ArrayList for holding our tweeters
     * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
     * 
     * @param Location
     * @author Scott Smiesko
     */
    public SubscriptionsManager(String Location, ButtonManager buttonMgrIn,
            RootGUI gui) {
        buttonMgr = buttonMgrIn;
        subscriptionVwr = new SubscriptionsViewer(this, buttonMgr);
        // subscriptVwer = new SubscriptViewer();
        // subscriptVwer.setVisible(false);
        // subscriptVwer.setVisible(true);
        gui.add(subscriptionVwr, BorderLayout.WEST);
        // gui.add(subscriptVwer, BorderLayout.WEST);
        subscriptionListLocation = Location;

        Document subscriptionList = null;
        // subscriptionList = XMLHelper.getDocumentByLocation(subscriptionListLocation);

        // if(subscriptionList != null)
        // initializeTweeters(subscriptionList);

    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //

    /**
     * Fills the subscribed tweeters based on the subscription document
     * 
     * @param subscriptionList
     */
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

        for (int t = 0; t < subscripts.getLength(); ++t) {
            subscript = (Element) (subscripts.item(t));
            isSearch = Boolean.parseBoolean(subscript.getAttribute("Search"));
            text = subscript.getTextContent();
            if (!isSearch)
                addTweeterSubscription(text);
            else
                addSubscription(new Search(text));
        }
        System.out.println("Array of tweeters is now constructed..");

    }

    /**
     * Removes user from the subscription list (removes from ArrayList and saves to XML)
     * 
     * @param name
     */
    public void removeSubscription(SubscriptionItem item) {
        if (subscriptionListLocation == null)
            throw new NullPointerException(
                    "Subscription list location was not initialized!");

        for (SubscriptionItem subscriptItem : _subscriptions)
            if (subscriptItem.equals(item)) {
                _subscriptions.remove(subscriptItem);

                try {
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
                break;
            }
        subscriptionVwr.refresh();
        // subscriptVwer.refresh(this, buttonMgr);
    }

    /**
     * Add new user to the subscription list (adds to ArrayList and saves to XML)
     * Checks for duplicates
     * 
     * @param name
     * @throws NullPointerException
     */
    public void addTweeterSubscription(String name) {
        Tweeter tweeter = new Tweeter(name);
        tweeter.addProgramStateListener(this);
    }

    public void addSubscription(SubscriptionItem item) {
        _subscriptions.add(item);
        subscriptionVwr.refresh();
    }

    @Override
    public void stateReceived(ProgramStateEvent event) {
        System.out.println("State Received: " + event.state());
        if (event.state() == ProgramState.SUBSCRIPTION_ADDED) {
            System.out.println("Add Subscription");
            addSubscription((SubscriptionItem) event.getSource());
        }
    }

    private synchronized void subscriptionAdded() {
        subscriptionVwr.refresh();
        // subscriptVwer.refresh(this, buttonMgr);
    }

    /**
     * Processes the XML document and commits
     * 
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
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
        // root.setAttribute("Sort")
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

    /**
     * Physically commits the information to a XML file.
     * 
     * @param newSubscriptions
     * @throws TransformerException
     */
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

    public ArrayList<SubscriptionItem> getSubscriptions() {
        return _subscriptions;
    }

    public SubscriptionItemViewer[] getSelected() {
        return subscriptionVwr.getSelected();
    }

}
