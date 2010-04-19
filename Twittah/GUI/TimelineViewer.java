package GUI;


import java.awt.GridLayout;

import javax.swing.JPanel;

import Changes.DisplayItem;
import Changes.Timeline;
import Timelines.CompositeTimeline;
import Twitter.Tweet;

/**
 * Displays all of the TweetContainer GUI objects in a "Timeline" feed
 * @author Frappe051
 *
 */
public class TimelineViewer extends JPanel
{
	/**
	 * 
	 * @param timeline - 
	 */
	/*
	public TimelineViewer(CompositeTimeline timeline)
	{
		setLayout(new GridLayout(0,1));

			for(Tweet tweet : timeline.getTweets())
			{
				add(new TweetViewer(tweet));
			}
		
	}*/
	
	public TimelineViewer(Timeline timeline)
	{
		setLayout(new GridLayout(0,1));

			for(DisplayItem tweet : timeline.displayItems())
			{
				add(new TweetViewer(tweet));
			}
		
	}
	

}