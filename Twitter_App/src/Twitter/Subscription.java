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
	
	private String expression = "//UserID";
	private ArrayList<Tweeter> tweeters = new ArrayList<Tweeter>();
	
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
	}

	/**
	 * Add new user to the subscription list (ArrayList and save to XML)
	 */
	
	public void addNewSubscription(String userID)
	{
		// XML processing
	}
	
	/**
	 * Testing method for showing if our tweeters array has been filled;
	 */
	public void showTweeters ()
	{
		for(int i=0; i<tweeters.size();i++)
		{
			System.out.println(tweeters.get(i).toString());
		}
	}

}
