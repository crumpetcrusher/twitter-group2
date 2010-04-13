package GUI;


import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import Timelines.Timeline;
import Twitter.Tweet;
import Twitter.Tweeter;

/**
 * Displays all of the TweetContainer GUI objects in a "Timeline" feed
 * @author Frappe051
 *
 */
public class TimelineViewer extends JPanel
{
	
	public TimelineViewer(ArrayList<Tweeter> subscribedTweeters)
	{
		
		add(new TweetViewer(new Tweet(subscribedTweeters.get(0), "OMGWTFBBQ", "LOL", new Date(), "text")));
		
		//for (Tweeter tweeter : subscribedTweeters)
		//{
			// Timeline tempTimeline = tweeter.getTimeline();
			/*for (Tweet tweet : tempTimeline.Tweets())
			{
				new TweetViewer(tweet);
			}*/
		//}

		
		
		
	}

}