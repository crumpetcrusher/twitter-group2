package Timelines;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import RandomClasses.Feed;
import RandomClasses.FeedItem;
import SortingClasses.*;
import Twitter.Tweet;

public class Timeline implements Feed {

	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	private List<FeedItem> feedItems = new ArrayList<FeedItem>();
	
	public void refresh()
	{
		for(Timeline timeline : timelines)
		{
			timeline.refresh();
		}
		clear();
		fill();
	}
	
	public FeedItem[] feedItems()
	{
		return (FeedItem[]) feedItems.toArray();
	}
	
	
	public void add(Feed feed)
	{
		timelines.add((Timeline)feed);
		clear();
		fill();
	}
	
	protected void addFeedItem(FeedItem newFeedItem)
	{
		feedItems.add(newFeedItem);
	}
	
	private void clear()
	{
		feedItems = new ArrayList<FeedItem>();
	}
	
	private void fill()
	{
		for(Timeline timeline : timelines)
			for(FeedItem feedItem : timeline.feedItems)
				addFeedItem(feedItem);
	}
	
	public String printFeeds()
	{
		String temp = "";
		
		for(Timeline timeline : timelines)
			temp += timeline.toString() + "\r\n" ;
		
		return temp;
	}
	
	public String toString()
	{
		String value = "[Timeline]" + "\r\n";
		for(FeedItem feedItem : feedItems)
			value += "\t" + feedItem.toString() + "\r\n";
		return value;
		
	}
	
	public void organizeByDate()
	{
		Collections.sort(feedItems, new ChronologicalFeedItem());
	}
	
	public void organizeByText()
	{
		Collections.sort(feedItems, new AlphabeticallyFeedItem());
	}
	
	public void organizeBySource(){}
	
	/*
    **
     * Gets a tweet by id.
     *
     * @author Rick Humes
     * @param id The id of the tweet.
     * @return The requested tweet. May be <code>null</code>.
     * @see Tweet
     *
    public Tweet getTweet(String tweetID)
    {
    	Tweet tweet_return = null;
    	if(feedItems != null) {
	    	for (Tweet tweet : feedItems) {
	    		if(tweet.getID().equals(tweetID)) {
	    			tweet_return = tweet.clone();
	    		}
	    	}
    	}
		return tweet_return;
    }
*/

	
    /*
     * Gets tweet(s) by date range.
     *
     * @author Rick Humes
     * @param beginDate The earliest tweet able to be shown.
     * @param endDate The latest tweet able to be shown.
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     *
    public Tweet[] getTweets(Date beginDate, Date endDate)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(feedItems != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : feedItems)
	    			if(tweet.getDate().compareTo(beginDate) >= 0 && tweet.getDate().compareTo(endDate) <= 0)
	    				requestedTweets.add(tweet);
	    		tweet_return = ((Tweet[])requestedTweets.toArray());
    		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return tweet_return;
    }*/
    
    /*
     * Gets tweet(s) by searching the text.
     *
     * @author Rick Humes
     * @param search Text to be searched for.
     * @param case_sensitive The search is case sensitive?
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     *
    public Tweet[] getTweets(String search, boolean case_sensitive)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(feedItems != null)
    		{
    			if(!case_sensitive)
    				search = search.toLowerCase();
    			
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
        		
	    		for (Tweet tweet : feedItems)
	    			if(tweet.getText().contains(search))
	    				requestedTweets.add(tweet);
	    		
	    		tweet_return = (requestedTweets.toArray(new Tweet[requestedTweets.size()]));
    		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return tweet_return;
    }*/

	
}
