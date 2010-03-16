package Timelines;

import java.util.ArrayList;
import java.util.List;

import Twitter.Tweet;
import Twitter.Tweeter;

public class MultipleTimeline extends Timeline {
	
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	public MultipleTimeline(Timeline[] newTimelines)
	{
		for(Timeline timeline : newTimelines)
			addTimeline(timeline);
	}
	
	public MultipleTimeline(Timeline newTimeline)
	{
		addTimeline(newTimeline);
	}
	
	private void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
		setTweets();
	}
	
	public void refresh()
	{
		for(Timeline timeline : timelines)
		{
			timeline.refresh();
		}
		clear();
		setTweets();
	}
	
	protected void setTweets()
	{
		List<Tweet> temp = new ArrayList<Tweet>();
		for(Timeline timeline : timelines)
		{
			for(Tweet tweet : timeline.getTweets())
				temp.add(tweet);
		}
		clear();
		setTweets(temp.toArray( new Tweet[0]));
	}

}
