package Twitter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import Exceptions.TweeterException;



/**
 * Deals with the subscription list we store locally of all the users we subscribe to
 * 
 * @author Frappe051
 *
 */
public class Subscriptions {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Attributes
	//
	
	/**
	 * Stores the arraylist of Tweeters (people we are subscribing to)
	 */
	private ArrayList<Tweeter> subscribedTweeters = new ArrayList<Tweeter>();
	
	/**
	 * Stores the file location of the subscription list.
	 */
	private String subscriptionListLocation;
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Constructors
	//
	
	/**
	 * Constructor for creating a new ArrayList for holding our tweeters
	 * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
	 * 
	 * @param subscriptionListLocationInput
	 * @author Scott Smiesko
	 */
	@SuppressWarnings("unchecked")
	public Subscriptions(String subscriptionListLocationInput)
	{
		subscriptionListLocation = subscriptionListLocationInput;
		
		Document doc = null;
		try {
			doc = new SAXBuilder().build(subscriptionListLocation);
		
		//Keeping this FileNotFoundException for more specific errors.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doc = null;
			subscriptionListLocationInput = null;
		} catch (Exception e)
		{
			e.printStackTrace();
			doc = null;
			subscriptionListLocationInput = null;
		}
		
		fillTweeters(doc);
		
		
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//  Class Methods
	//
	
	/**
	 * Fills the subscribed tweeters based on the subscription document
	 * 
	 * @param doc
	 */
	private void fillTweeters(Document doc)
	{
		List<Element> subscription = doc.getRootElement().getChildren("User");

		for(Element element : subscription)
		{
			String userID = element.getChildText("UserID");
			try{
				Tweeter newTweeter = new Tweeter(userID);
				subscribedTweeters.add(newTweeter);
			}catch(TweeterException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Array of tweeters is now constructed..");
	}
	
	/**
	 * Removes user from the subscription list (removes from ArrayList and saves to XML)
	 */
	public void removeSubscription(String userID)
	{	
		if(subscriptionListLocation == null)
			throw new NullPointerException("Subscription list location was not initialized!");
		
		for(Tweeter tweeter : subscribedTweeters)
			if (tweeter.getUserID().equals(userID))
			{
				subscribedTweeters.remove(tweeter);
				writeDocument();
			}
	}
	
	/**
	 * Add new user to the subscription list (adds to ArrayList and saves to XML)
	 * 
	 * Checks for duplicates
	 */
	public void addNewSubscription(String userID) throws NullPointerException
	{
		if(subscriptionListLocation == null)
			throw new NullPointerException("Subscription list location was not initialized!");
		
		boolean exists = false;
		
		for(Tweeter tweeter : subscribedTweeters)
			if (tweeter.getUserID().equals(userID))
				exists = true;
		
		if(!exists)
		{
			try{
				Tweeter newTweeter = new Tweeter(userID);
				subscribedTweeters.add(newTweeter);
				writeDocument();
			}
			catch(TweeterException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Processes the XML document and commits
	 */
	public void writeDocument()
	{
		if(subscriptionListLocation == null)
			throw new NullPointerException("Subscription list location was not initialized!");
		
        Element root = new Element("Subscriptions");
        Document doc = new Document(root);
        
		for(Tweeter tweeter : subscribedTweeters)
		{
			Element user = new Element("User");
			Element userID = new Element("UserID");
	        userID.setText(tweeter.getUserID());
	        
	        user.addContent(userID);
	        root.addContent(user);
        }
		
		commitSubscriptions(doc);
	}
	
	/**
	 * Physically commits the information to a XML file.
	 * @param doc
	 */
	private void commitSubscriptions(Document doc)
	{

	      try {
	    	  	OutputStream stream = new FileOutputStream(subscriptionListLocation);
	    	    XMLOutputter outputter = new XMLOutputter();
	    	    outputter.output(doc, stream);
	    	    stream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Tweeter> getSubscriptions(){
		return subscribedTweeters;
	}

}
