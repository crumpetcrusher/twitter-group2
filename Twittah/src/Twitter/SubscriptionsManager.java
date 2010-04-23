package Twitter;

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

import Changes.OrganizeType;
import Changes.Timeline;
import Exceptions.TweeterException;
import Timelines.SearchTimeline;
import backend.XMLHelper;



/**
 * Deals with the subscription list we store locally of all the users we subscribe to
 * 
 * @author Frappe051
 *
 */
public class SubscriptionsManager {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Attributes
	//
	
	/**
	 * Stores the arraylist of Tweeters (people we are subscribing to)
	 */
	private ArrayList<Tweeter> subscribedTweeters = new ArrayList<Tweeter>();
	
	private Timeline compositeTimeline = new Timeline();
	
	/**
	 * Stores the file location of the subscription list.
	 */
	private String subscriptionListLocation;
	
	public void setOrganizeType(OrganizeType type) {
		compositeTimeline.setOrganizeType(type);
		
	}

	
	public void fillCompositeTimeline() {
				compositeTimeline.clear();
				for (Tweeter tweeter : subscribedTweeters)
				{
					tweeter.getUserTimeline().update();
					compositeTimeline.addTimeline(tweeter.getUserTimeline());
				}
				compositeTimeline.fill();
				compositeTimeline.organize();
	}
	
	public void clearTimeline() {

				compositeTimeline.clear();

	}
	
	public void addUserToTimeline(String name) {

				for(Tweeter tweeter : subscribedTweeters){
					if (tweeter.getUserName().equals(name)) {
						compositeTimeline.addTimeline(tweeter.getUserTimeline());
					}
				}
				compositeTimeline.fill();
				compositeTimeline.organize();
				
	}
	
	public void removeUserFromTimeline(String name) {

				for(Tweeter tweeter : subscribedTweeters){
					if (tweeter.getUserName().equals(name)) {
						compositeTimeline.removeTimeline(tweeter.getUserTimeline());
					}
				}
				compositeTimeline.fill();
				compositeTimeline.organize();

	}
	
	public void addSearchToTimeline(String[] query) {
		SearchTimeline searchTimeline = new SearchTimeline(query);
		compositeTimeline.addTimeline(searchTimeline);
		compositeTimeline.fill();
		compositeTimeline.organize();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Constructors
	//
	
	/**
	 * Constructor for creating a new ArrayList for holding our tweeters
	 * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
	 * @param Location 
	 * @author Scott Smiesko
	 */
	public SubscriptionsManager(String Location)
	{
		
		subscriptionListLocation = Location;
		
		Document subscriptionList = null;
		subscriptionList = XMLHelper.getDocumentByLocation(subscriptionListLocation);
		
		initializeTweeters(subscriptionList);
		initializeTimeline();
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Methods
	//
	
	/**
	 * Fills the subscribed tweeters based on the subscription document
	 * @param subscriptionList 
	 */
	private void initializeTweeters(Document subscriptionList)
	{
		
		// First, we fill our tweeters
		//
		Element 	subscriptions;
		
		NodeList	tweeters;
		Element		tweeter;
		String	 	name;
		
		subscribedTweeters = new ArrayList<Tweeter>();
		
		subscriptions = (Element)(subscriptionList.getDocumentElement());
		
		tweeters = subscriptions.getElementsByTagName("name");
		
		for (int t=0; t < tweeters.getLength(); ++t)
		{
			tweeter = (Element)(tweeters.item(t));
			name = tweeter.getTextContent();
			
			addSubscription(name);
				
		}
		
		
		System.out.println("Array of tweeters is now constructed..");
		
	}
	
	private void initializeTimeline()
	{
		for (Tweeter tweeter : subscribedTweeters)
		{
			compositeTimeline.addTimeline(tweeter.getUserTimeline());
		}
		compositeTimeline.fill();
		compositeTimeline.organize();
	}
	
	/**
	 * Removes user from the subscription list (removes from ArrayList and saves to XML)
	 * @param name 
	 */
	public void removeSubscription(String name)
	{	
		if(subscriptionListLocation == null)
			throw new NullPointerException("Subscription list location was not initialized!");
		
		for(Tweeter tweeter : subscribedTweeters)
			if (tweeter.getUserName().equals(name))
			{
				subscribedTweeters.remove(tweeter);
				
				try {
					writeDocument();
					System.out.println("User has been removed");
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * Add new user to the subscription list (adds to ArrayList and saves to XML)
	 * Checks for duplicates
	 * @param name 
	 * @throws NullPointerException 
	 */
	public void addSubscription(String name) throws NullPointerException
	{
		if(subscriptionListLocation == null)
			throw new NullPointerException("Subscription list was not initialized!");
		
		boolean exists = false;
		
		for(Tweeter tweeter : subscribedTweeters)
			if (tweeter.getUserName().equals(name))
				exists = true;
		
		if(!exists)
		{
			try{
				subscribedTweeters.add(new Tweeter(name));
				writeDocument();
			}
			catch(TweeterException e)
			{
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("User already exists");
		}
	}
	
	/**
	 * Processes the XML document and commits
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 */
	public void writeDocument() throws ParserConfigurationException, TransformerException
	{
		if(subscriptionListLocation == null) {
			throw new NullPointerException("Subscription list was not initialized!");
		}
		
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document newSubscriptions = docBuilder.newDocument();
		
		Element root = newSubscriptions.createElement("Subscriptions");
		newSubscriptions.appendChild(root);
		
		for (Tweeter tweeter : subscribedTweeters)
		{
			Element child = newSubscriptions.createElement("name");
			root.appendChild(child);
			
			Text text = newSubscriptions.createTextNode(tweeter.getUserName());
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
		
		File file = new File(subscriptionListLocation);
		Result result = new StreamResult(file);
		
		Transformer xformer = TransformerFactory.newInstance().newTransformer();

		xformer.setOutputProperty(OutputKeys.INDENT, "yes");
		xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		xformer.setOutputProperty(OutputKeys.METHOD, "xml");
		
		xformer.transform(source, result);
		
		
	}
	
	public ArrayList<Tweeter> getSubscriptions(){
		return subscribedTweeters;
	}
	
	public Timeline getCompositeTimeline(){
		return compositeTimeline;
	}

}
