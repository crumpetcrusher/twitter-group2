package Twitter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Exceptions.TweeterException;
import RandomClasses.*;
import SortingClasses.*;
import Timelines.*;
import TestingClasses.*;

// Will need this soon: import javax.swing.*;

/**
 * Main class of the program
 */
public class Main {

	static JFrame frame;
	static JPanel mainContainer;
	static JSplitPane mainSplitPane;
	static JPanel feedPanel;
	static JScrollPane scrollPane;
	
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
			testFeedStuff();
			//testTweeter();
			//testSearch();
		
		// This will be where we create our GUI object and show it, but
		// for now, it will just be a blank main class
		
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
