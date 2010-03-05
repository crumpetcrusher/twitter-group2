package Timelines;

import java.util.ArrayList;
import java.util.List;

import Twitter.Tweet;
import Twitter.Tweeter;

public class MultipleTimeline extends Timeline {
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	public MultipleTimeline(String[] userIDs)
	{
		for(String userID : userIDs)
			addUserTimeline(new Tweeter(userID));
	}
	
	public MultipleTimeline(Tweeter[] tweeters)
	{
		for(Tweeter tweeter : tweeters)
			addUserTimeline(tweeter);
	}
	
	public MultipleTimeline(Timeline[] newTimelines)
	{
		for(Timeline timeline : newTimelines)
			addTimeline(timeline);
	}
	
	public void addUserTimeline(Tweeter tweeter)
	{
		UserTimeline temp = new UserTimeline(tweeter);
		addTimeline(temp);
	}
	
	public void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
		updateTweets();
	}
	
	public void updateTweets()
	{
		List<Tweet> temp = new ArrayList<Tweet>();
		for(Timeline timeline : timelines)
			for(Tweet tweet : timeline.getTweets())
				temp.add(tweet);
		clear();
		setTweets((Tweet[])temp.toArray());
	}

}
