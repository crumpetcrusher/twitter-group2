package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import Twitter.Subscriptions;
import Twitter.Tweet;
import Twitter.Tweeter;

public class Main{

	/**
	 * GUI creation
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	public static Container TwitterApp() throws Exception
	{
		
		Subscriptions mainSubscriptions = new Subscriptions("src/subscriptionlist.xml");
		ArrayList<Tweeter> subscribedTweeters = mainSubscriptions.getSubscriptions();
		
		
		// GUI Container
		//
		Container GUI = new JPanel();
		GUI.setLayout(new BorderLayout());
		
		
		// Timeline Viewer
		//
		Container TimelineViewer = new JPanel();
        //content.add(new TweetViewer(new Tweet(subscribedTweeters.get(0), "HI", "lolj;laksdjf;avoiandfpoiajsdponasdoifpaosdijhfaoisdfhjajvnsdlaubdiuhasdf", new Date(), "web")));
        
        
        // Add everything to our GUI container
        //
        GUI.add(new UserList(subscribedTweeters), BorderLayout.WEST);
        GUI.add(new TimelineViewer(subscribedTweeters), BorderLayout.EAST);
        
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
        TwitterApp.setSize(300,400);
        TwitterApp.setVisible(true);
	}
	
}
