package Twitter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.XMLOutputter;



/**
 * Deals with the subscription list we store locally of all the users we subscribe to
 * 
 * @author Frappe051
 *
 */
public class Subscriptions {
	
	/**
	 * Stores the arraylist of Tweeters (people we are subscribing to)
	 */
	private ArrayList<Tweeter> subscribedTweeters = new ArrayList<Tweeter>();
	
	/**
	 * Constructor for creating a new ArrayList for holding our tweeters
	 * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
	 * 
	 * @param subscriptionListLocation
	 * @author Scott Smiesko
	 */
	@SuppressWarnings("unchecked")
	public Subscriptions(String subscriptionListLocation)
	{
		Document doc = null;
		try {
			doc = new SAXBuilder().build(subscriptionListLocation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doc = null;
		}
		
		fillTweeters(doc);
	}
	
	/**
	 * Fills the subscribed tweeters based on the subscription document
	 * 
	 * @param doc
	 */
	public void fillTweeters(Document doc)
	{
		List subscription = doc.getRootElement().getChildren("User");
		
		for (int i=0; i<subscription.size(); i++)
		{
			String userID = ((Element)subscription.get(i)).getChildText("UserID");
			subscribedTweeters.add(new Tweeter(userID));
		}
		
		System.out.println("Array of tweeters is now constructed..");
	}

	/**
	 * Removes user from the subscription list (removes from ArrayList and saves to XML)
	 */
	public void removeSubscription(String userID)
	{		
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
	public void addNewSubscription(String userID)
	{
		boolean exists = false;
		
		for(Tweeter tweeter : subscribedTweeters)
			if (tweeter.getUserID().equals(userID))
				exists = true;
		
		if(!exists)
		{
			subscribedTweeters.add(new Tweeter(userID));
			writeDocument();
		}
	}
	
	/**
	 * Processes the XML document and commits
	 */
	public void writeDocument()
	{
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
	    	  	OutputStream stream = new FileOutputStream("bin/subscriptionlist.xml");
	    	    XMLOutputter outputter = new XMLOutputter();
	    	    outputter.output(doc, stream);
	    	    stream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Testing method for showing if our tweeters array has been filled;
	 * 
	 * Calls each tweeter object in our tweeters array, asks for toString() operation
	 * 
	 * @author Scott Smiesko
	 */
	public void printTweeters ()
	{
		for(Tweeter tweeter : subscribedTweeters)
		{
			System.out.println(tweeter.toString());
		}
	}

}
