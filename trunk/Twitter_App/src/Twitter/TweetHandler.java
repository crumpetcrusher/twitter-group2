package Twitter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public class TweetHandler {

	
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	public TweetHandler()
	{
		
	}
	
	/**
	 * Prints out all tweets held in this timeline.
	 */
	public void printTweets(PrintStream stream)
	{
		stream.println("Printing tweets");
		for(Tweet tweet : tweets)
			stream.println(tweet.toString());
	}

    /**
     * Gets a tweet by id.
     *
     * @author Rick Humes
     * @param id The id of the tweet.
     * @return The requested tweet. May be <code>null</code>.
     * @see Tweet
     */
    public Tweet getTweet(String tweetID)
    {
    	Tweet tweet_return = null;
    	if(tweets != null) {
	    	for (Tweet tweet : tweets) {
	    		if(tweet.getID().equals(tweetID)) {
	    			tweet_return = tweet;
	    		}
	    	}
    	}
		return tweet_return;
    }
	
    /**
     * 
     * Gets every tweet
     * 
     * @author Scott Smiesko
     * @param null
     * @return every tweet
     * @see Tweet
     */
    public Tweet[] getTweets()
    {
    	Tweet[] temp = new Tweet[tweets.size()];
    	tweets.toArray(temp);
    	return temp;
    }


    /**
     * Gets tweet(s) by date range.
     *
     * @author Rick Humes
     * @param beginDate The earliest tweet able to be shown.
     * @param endDate The latest tweet able to be shown.
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(Date beginDate, Date endDate)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweets != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : tweets)
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
    }
    
    /**
     * Gets tweet(s) by searching the text.
     *
     * @author Rick Humes
     * @param search Text to be searched for.
     * @param case_sensitive The search is case sensitive?
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(String search, boolean case_sensitive)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweets != null)
    		{
    			if(!case_sensitive)
    				search = search.toLowerCase();
    			
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
        		
	    		for (Tweet tweet : tweets)
	    			if(tweet.getText().toLowerCase().contains(search))
	    				requestedTweets.add(tweet);
	    		
	    		tweet_return = (requestedTweets.toArray(new Tweet[requestedTweets.size()]));
    		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return tweet_return;
    }
	
	
}
