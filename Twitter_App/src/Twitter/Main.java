package Twitter;
import Timelines.User_Timeline;

// Will need this soon: import javax.swing.*;

/**
 * Main class of the program
 */
public class Main {

	/**
	 * 
	 * main object to run the program
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		//Testing Methods
		
		//testSubscriptions();
		testUserTimeline();
		
		
		// This will be where we create our GUI object and show it, but
		// for now, it will just be a blank main class
		
	}
	
	public static void testUserTimeline()
	{
		User_Timeline timeline = new User_Timeline("14103500");
		TweetHandler tweetHandler = new TweetHandler();
		Tweet[] tweets = timeline.getTweets();
		tweetHandler.organizeByDate(tweets, true);
		tweetHandler.printTweets(tweets, System.out);
	}
	
	public static void testSubscriptions()
	{
		Subscriptions subscriptions = new Subscriptions("bin/subscriptionlist.xml");
		//subscriptions.addNewSubscription("14103500");
		subscriptions.printTweeters(System.out);

	}
}
