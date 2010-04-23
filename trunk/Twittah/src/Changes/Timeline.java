package Changes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Timelines.UserTimeline;

public class Timeline {
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	private List<DisplayItem> displayItems = new ArrayList<DisplayItem>();
	
	private OrganizeType currentOrganize  = OrganizeType.JAN_DEC;
	
	
	public DisplayItem[] displayItems()
	{
		DisplayItem[] temp = new DisplayItem[displayItems.size()]; 
		return displayItems.toArray(temp);
	}
	
	
	public void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
	}
	
	protected void addDisplayItem(DisplayItem newDisplayItem)
	{
		displayItems.add(newDisplayItem);
	}
	
	public void clear()
	{
		timelines.clear();
		displayItems.clear();
	}
	
	public void fill()
	{
		for(Timeline timeline : timelines)
		{
			for(DisplayItem displayItem : ((UserTimeline)timeline).getUserTweets())
			{
				
				addDisplayItem(displayItem);
			}
		}
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
	
	public void organize()
	{
		Collections.sort(displayItems, new DisplayItemOrganizer(currentOrganize));
	}


	public void removeTimeline(Timeline timeline) {
		//String toDeleteTimelineName = ((UserTimeline) timeline).getUserTweets().get(0).getTweeter().getUserName();
		
		for(Timeline temp : timelines) {
			if (temp.equals(timeline)){
				timelines.remove(timeline);
			}
		}
		
	}


	public void setOrganizeType(OrganizeType type) {
		currentOrganize = type;
		
	}
	
	
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
