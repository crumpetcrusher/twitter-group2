package backend;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
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
import GUI.SubscriptionsViewer;
import GUI.T_Main;
import GUI.TimelinesViewer;
import Timelines.SearchTimeline;
import Timelines.UserTimeline;
import Twitter.Tweeter;

public class ButtonManager {
	private SubscriptionsManager subscriptionsMgr;
	private TimelinesManager	 timelinesMgr;
	private String settingsLocation = "src/settings.xml";

	public ButtonManager(RootGUI gui)
	{
	    subscriptionsMgr = new SubscriptionsManager("src/settings.xml", this, gui);
	    timelinesMgr     = new TimelinesManager(subscriptionsMgr, gui);
	    init(XMLHelper.getDocumentByLocation("src/settings.xml"));
	}
	
	private void init(Document doc)
	{
	    if(doc != null)
	    {
                Element         subscriptions;
                
                NodeList        subscripts;
                Element         subscript;
                Element         search;
                String          text;
                boolean         isSearch;
                
                
                subscriptions = (Element)(doc.getDocumentElement());
                timelinesMgr.organize(OrganizeType.valueOf(subscriptions.getAttribute("Sort")));
                
                subscripts = subscriptions.getElementsByTagName("name");
                
                for (int t=0; t < subscripts.getLength(); ++t)
                {
                        subscript = (Element)(subscripts.item(t));
                        isSearch = Boolean.parseBoolean(subscript.getAttribute("Search"));
                        text = subscript.getTextContent();
                        if(!isSearch)
                            subscriptionsMgr.addTweeterSubscription(text);
                        else
                            subscriptionsMgr.addSubscription(new Search(text));
                }
	    }
	}
	
	public void doAddSubscriptionTweeter(String newName) {
	    subscriptionsMgr.addTweeterSubscription(newName);
	}
	
	public void addDisplaySubscription(SubscriptionItem item)
	{
	    timelinesMgr.addToTimeline(item);
	}
	
	public void removeDiaplySubscription(SubscriptionItem item)
	{
	    timelinesMgr.removeFromTimeline(item);
	}
	
	public void doDeleteSubscription(SubscriptionItem item) {
		subscriptionsMgr.removeSubscription(item);
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
	}
	
	public void doRefreshTimeline() {
		timelinesMgr.refreshTimeline();
	}
	
	public void doSelectSubscription(SubscriptionItem item) {
		timelinesMgr.clearTimeline();
		timelinesMgr.addToTimeline(item);
	}
	
	public void doShowCompositeTimeline() {
		timelinesMgr.clearTimeline();
		for(SubscriptionItemViewer item : subscriptionsMgr.getSelected())
		    timelinesMgr.addTimeline(item.timeline());
		//timelinesMgr.initialize();
	}
	
	public void toggle(OrganizeType type)
	{
		OrganizeType current = timelinesMgr.organizeType();	
		if (type == OrganizeType.A_Z || type == OrganizeType.Z_A)
			type = (current == OrganizeType.A_Z ? OrganizeType.Z_A : OrganizeType.A_Z);
		else
			type = (current == OrganizeType.JAN_DEC ? OrganizeType.DEC_JAN : OrganizeType.JAN_DEC);
		
		timelinesMgr.organize(type);
	}

	public void doSearch(String query) {
		System.out.println("Searching for " + query);
		//subscriptionsMgr.addSubscription(query);
		timelinesMgr.clearTimeline();
		subscriptionsMgr.addSubscription(new Search(query));
		timelinesMgr.addSearchToTimeline(query);
	}
	
	public void systemShutdown()
	{
		timelinesMgr.deletePreviousTimelines();
		timelinesMgr.saveTimelines();
		try {
            writeDocument();
        }
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	       /**
         * Processes the XML document and commits
         * @throws ParserConfigurationException 
         * @throws TransformerException 
         */
        private void writeDocument() throws ParserConfigurationException, TransformerException
        {
                
                DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                Document newSubscriptions = docBuilder.newDocument();
                
                Element root = newSubscriptions.createElement("Subscriptions");
                root.setAttribute("Sort", timelinesMgr.organizeType().toString());
                newSubscriptions.appendChild(root);
                
                for (SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())
                {
                        Element child = newSubscriptions.createElement("name");
                        root.appendChild(child);
                        child.setAttribute("Search", Boolean.toString(subscriptItem.isSearch()));
                        
                        Text text = newSubscriptions.createTextNode(subscriptItem.text());
                        child.appendChild(text);
                }

                
                commitSubscriptions(newSubscriptions);
                
        }
        
        /**
         * Physically commits the information to a XML file.
         * @param newSubscriptions 
         * @throws TransformerException 
         */
        private void commitSubscriptions(Document newSubscriptions) throws TransformerException
        {
                
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
