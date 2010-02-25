package Twitter;


/**
 * A lot of different changes to Main.java
 * 
 * @author Scott Smiesko
 */
public class T_Main {

	public static void main(String args[])
	{
		
		// Let's have some fun
		// This twitter object of type "Subscription" is the -bees knees- of the program
		
		// This class lets us pass it a subscriptionlist.xml of just "userID" values
		// and It will populate an array of type "Tweeter" to store all of the UserIDs (constructor)
		// which at that point, the constructor for each "tweeter" in the array will pull down a
		// user_feed.xml and store it, then it will parse the XML document to do
		// .set operations on each tweeter object.
		
		// Classy, no?
		
		final String subscriptionList = "subscriptionlist.xml";
		Subscription twitter = new Subscription(subscriptionList);
		
		twitter.showTweeters();
		
	}
	
}
