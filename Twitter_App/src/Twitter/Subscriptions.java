package Twitter;

import java.util.*;
import org.jdom.*;
import org.jdom.input.*;



/**
 * Deals with the subscription list we store locally of all the users we subscribe to
 * 
 * @author Frappe051
 *
 */
public class Subscription {
	
	/**
	 * Stores the arraylist of Tweeters (people we are subscribing to)
	 */
	private ArrayList<Tweeter> tweeters = new ArrayList<Tweeter>();
	
	/**
	 * Constructor for creating a new ArrayList for holding our tweeters
	 * Uses JDOM to populate a List to store the userIDs before passing to "tweeters"
	 * 
	 * @param subscriptionList
	 * @author Scott Smiesko
	 */
	@SuppressWarnings("unchecked")
	public Subscription(String subscriptionList)
	{
		Document doc = null;
		try {
			doc = new SAXBuilder().build(subscriptionList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doc = null;
		}
		List subscription = doc.getRootElement().getChildren("User");
		for (int i=0; i<subscription.size(); i++)
		{
			String userID = ((Element)subscription.get(i)).getChildText("UserID");
			tweeters.add(new Tweeter(userID));
		}
		System.out.println("array of tweeters is now constructed..");
	}

	/**
	 * Add new user to the subscription list (adds to ArrayList and saves to XML)
	 */
	public void addNewSubscription(String userID)
	{
		// XML processing, nothing coded yet.
	}
	
	/**
	 * Testing method for showing if our tweeters array has been filled;
	 * 
	 * Calls each tweeter object in our tweeters array, asks for toString() operation
	 * 
	 * @author Scott Smiesko
	 */
	public void showTweeters ()
	{
		for(int i=0; i<tweeters.size();i++)
		{
			System.out.println(tweeters.get(i).toString());
		}
	}

}
