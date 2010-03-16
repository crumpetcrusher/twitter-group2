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
import GUI.CustomObject1;
import GUI.TextFieldButton;
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
		
			draw();
			//testSubscriptions();
			testFeedStuff();
			//testTweeter();
		
		// This will be where we create our GUI object and show it, but
		// for now, it will just be a blank main class
		
	}
	
	public static void draw()
	{
	    frame = new JFrame("Test Frame");
	    mainContainer = new JPanel();
	    mainContainer.setLayout(new BorderLayout());
	    //feedPanel = new JPanel();
	    //feedPanel.setLayout(new GridLayout(0, 1));
	    //mainContainer.add(feedPanel, BorderLayout.CENTER);
	    mainContainer.add(new JButton("Jiggers"), BorderLayout.EAST);
	    mainContainer.add(new JButton("Jiggers"), BorderLayout.WEST);
	    //mainContainer.add(new JButton("Niggers"));
	    //JPanel secondaryContainer = new JPanel();
	    //mainContainer.add(new JButton("Lovers"));
	    
	    frame.getContentPane().add(mainContainer);
	    //frame.getContentPane().add(secondaryContainer);
	    
	    //frame.getContentPane().add(mainContainer);
	    frame.setSize(400, 400);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.BLACK);
		//mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainContainer.add(scrollPane, BorderLayout.CENTER);
		//scrollPane.setViewportView(feedPanel);
		/*
		frame.getContentPane().add(mainSplitPane);
		
		mainSplitPane.add(scrollPane, JSplitPane.LEFT);
	    
	    //CustomObject1 button = new CustomObject1(new Tweeter("24973163"));  // Initialize the component.
	    //frame.getContentPane().add(button);      // Place the component on the application
	                                             // window such that it fills the whole
	                                             // window frame.
	                                              * 
	                                              */
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
			System.out.println(tweeter);
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
				//new MultipleTimeline( new Timeline[] {new UserTimeline(new Tweeter("24973163")), new UserTimeline(new Tweeter("14103500"))} );
				new UserTimeline(new Tweeter("14103500"));
		
		//Feed Organize
			//feed.organizeByDate();
			//feed.organizeBySource();
			//feed.organizeByText();
		
		//Feed GetFeedItems
			FeedItem[] feedItems = feed.feedItems();
		
		//Feed Refresh
			//feed.refresh();
		
		//Feed Print
			System.out.println(feed);
			
			
		    feedPanel = new JPanel();
		    //feedPanel.setLayout(new BorderLayout());
		    feedPanel.setLayout(new GridLayout(0,1));
		    //feedPanel.setLayout(new FlowLayout());
		    /*
			for(FeedItem feedItem : feedItems)
			{
				System.out.println("Adding FeedItem to GUI");
				//mainContainer.add(new JButton(feedItem.source()));
				//feedPanel.add(new JButton("Assholes"));
				feedPanel.add(new CustomObject1(feedItem));
				//mainContainer.repaint();
				//System.out.println(feedItem);
			}*/
		    /*JPanel obj2 = //new TextFieldButton();
		    	new CustomObject1("Fuck em");
		    JPanel obj1 = //new TextFieldButton();
		    	//new CustomObject1();
		    feedPanel.add(obj1);
		    feedPanel.add(obj2);
		    obj2.setVisible(true);
		    feedPanel.repaint();
		    feedPanel.add(new JButton("asshates"));
		    feedPanel.add(new JButton("asshates"));
		    feedPanel.setBackground(Color.BLACK);
			scrollPane.setViewportView(feedPanel);
			//scrollPane.re
		    frame.setVisible(true);*/
			
		}
		catch(TweeterException e)
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
