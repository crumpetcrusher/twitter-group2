package Twitter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import Exceptions.TweeterException;
import GUI.CompositeTimeline;
import RandomClasses.Feed;
import Timelines.Timeline;
import Timelines.UserTimeline;


/**
 * Main class of the program
 */
public class TwitterApp extends JFrame{
	
	
	/**
	 * Constructor to build our TwitterApp GUI
	 * @throws TweeterException 
	 */
	public TwitterApp() throws TweeterException
	{
		
		Container content = new JPanel();
        content.setLayout(new BorderLayout());
        
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet(new Tweeter("24973163"), "123", "OMG DOES IT WORK?!", new Date(), "web"));
        tweets.add(new Tweet(new Tweeter("24973164"), "123", "OMG DOES IT WORK?!", new Date(), "web"));

        
        content.add(new CompositeTimeline(tweets));
        
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");

        menubar.add(file);
        setJMenuBar(menubar);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400);
        setLocation(200,200);
        setContentPane(content);
        setVisible(true);
        
        
        
	}
	
	/**
	 * main object to run the program
	 * 
	 * @param args
	 * @throws TweeterException 
	 */
	public static void main(String[] args) throws TweeterException
	{
		
		// Testing Methods for Console Output
		
			//testSubscriptions();
			//testFeedStuff();
			//testTweeter();
			//testSearch();
		
			
		// Testing Methods for GUI Output
		new TwitterApp();
		
		// oh hey look at that it does stuff
		
	}
	
	public static void testSearch()
	{
		//Search search = new Search("Obama");
		//search.printTweets(System.out);
	}
	
	public static void testTweeter()
	{
		//Un-comment to test functionality
		
		try{
		//Tweeter Creation
			//Valid Tweeter
				Tweeter tweeter = new Tweeter("24973163");
			//Invalid Tweeter
				//Tweeter tweeter = new Tweeter("-1");
			
		//Tweeter Get Testing
			//String testString = "[Testing Tweeter Functions]";
			//testString += tweeter.getRealName();
			//testString += tweeter.getScreenName();
			//testString += tweeter.getUserID();
			//System.out.println(testString);
			
		//Tweeter toString
			//System.out.println(tweeter);
		}
		catch(TweeterException e)
		{
			//e.printError();
			//e.printStackTrace();
		}
	}
	
	public static void testFeedStuff()
	{
		//Un-comment to test functionality
		try
		{
		//Feed Creation
			Feed feed = 
				new Timeline();
				//Fill The MultipleTimeline
					feed.add(new UserTimeline("24973163"));
					feed.add(new UserTimeline("14103500"));
				//new UserTimeline("14103500");
		
		//Feed Organize
			//feed.organizeByDate();
			//feed.organizeBySource();
			//feed.organizeByText();
		
		//Feed GetFeedItems
			//FeedItem[] feedItems = feed.feedItems();
		
		//Feed Refresh
			//feed.refresh();
		
		//Feed Print
			System.out.println(feed);
			//System.out.println(((Timeline)feed).printFeeds());
			
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public static void testSubscriptions()
	{
		Subscriptions subscriptions = new Subscriptions("src/subscriptionlist.xml");
		//subscriptions.addNewSubscription("14103500");
		subscriptions.printTweeters(System.out);

	}
}
