package Changes;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Timeline {

	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	private List<DisplayItem> displayItems = new ArrayList<DisplayItem>();
	
	private OrganizeType currentOrganize  = OrganizeType.JAN_DEC;
	
	public void refresh()
	{
		for(Timeline timeline : timelines)
		{
			timeline.update();
		}
		clear();
		fill();
		organizeBy(currentOrganize);
	}
	
	public DisplayItem[] displayItems()
	{
		DisplayItem[] temp = new DisplayItem[displayItems.size()]; 
		return displayItems.toArray(temp);
	}
	
	
	public void add(Timeline timeline)
	{
		timelines.add(timeline);
		clear();
		fill();
	}
	
	protected void addDisplayItem(DisplayItem newDisplayItem)
	{
		displayItems.add(newDisplayItem);
	}
	
	private void clear()
	{
		displayItems = new ArrayList<DisplayItem>();
	}
	
	private void fill()
	{
		for(Timeline timeline : timelines)
			for(DisplayItem displayItem : timeline.displayItems)
				addDisplayItem(displayItem);
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
		for(DisplayItem displayItem : displayItems)
			value += "\t" + displayItem.toString() + "\r\n";
		return value;
		
	}
	
	public void organizeBy(OrganizeType type)
	{
		currentOrganize = type;
		Collections.sort(displayItems, new DisplayItemOrganizer(type));
	}
	
	public void update() {
		refresh();		
	};
	
	
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