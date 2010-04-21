package GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Changes.Timeline;
import Twitter.SubscriptionsManager;
import Twitter.Tweeter;

public class Main{

	/**
	 * GUI creation
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	/*
	
	public static Container TwitterApp() throws Exception
	{
		
		SubscriptionsManager mainSubscriptions = new SubscriptionsManager("subscriptionlist.xml");
		CompositeTimeline timeline = new CompositeTimeline();
		
		
		
		for(Tweeter tweeter : mainSubscriptions.getSubscriptions())
		{
			timeline.add(tweeter.getUserTimeline().getUserTweets());
		}
		
		
		// GUI Container
		//
		Container GUI = new JPanel();
		GUI.setLayout(new BorderLayout());

		
		// TimelineViewer Container
		//
		JScrollPane TLView = new JScrollPane(new TimelineViewer(timeline));
		
		TLView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        
        // Add everything to our GUI container
        //
        GUI.add(new UserList(mainSubscriptions.getSubscriptions()), BorderLayout.WEST);
        GUI.add(TLView, BorderLayout.EAST);
        
        return GUI;
	}*/
	public static Container TwitterApp() throws Exception
	{
		
		SubscriptionsManager mainSubscriptions = new SubscriptionsManager("subscriptionlist.xml");
		
		mainSubscriptions.addNewSubscription("frappe051");
		mainSubscriptions.addNewSubscription("soljia");
		

		// GUI Container
		//
		Container GUI = new JPanel();
		GUI.setLayout(new BorderLayout());

		
		// TimelineViewer Container
		//
		JScrollPane TLView = new JScrollPane(new TimelineViewer(mainSubscriptions.getCompositeTimeline()));
		
		TLView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        
        // Add everything to our GUI container
        //
        GUI.add(new UserList(mainSubscriptions.getSubscriptions()), BorderLayout.WEST);
        GUI.add(TLView, BorderLayout.EAST);
        
        return GUI;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{		
		
		JFrame TwitterApp = new JFrame("Twittah!");
		
        // Menubar
        //
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        menubar.add(file);
        
        // TwitterApp Properties
        //
        TwitterApp.setJMenuBar(menubar);
		TwitterApp.setContentPane(TwitterApp());
        TwitterApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TwitterApp.setSize(600,600);
        TwitterApp.setVisible(true);
	}
	
	
}
