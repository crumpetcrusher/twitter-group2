package GUI;


import java.util.ArrayList;

import javax.swing.JPanel;

import Twitter.Tweet;

/**
 * Displays all of the TweetContainer GUI objects in a "Timeline" feed
 * @author Frappe051
 *
 */
public class CompositeTimeline extends JPanel
{
	
	public CompositeTimeline(ArrayList<Tweet> tweets)
	{
		for (Tweet tweet : tweets)
		{
			add(new TweetContainer(tweet));
		}

		
		
		
	}

}