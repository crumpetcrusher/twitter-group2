package Twitter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import SortingClasses.AlphabeticallyTweet;
import SortingClasses.ChronologicalTweet;


/**
 * This class will do functions on an array of tweets.
 * @author Rick Humes
 *
 */
public class TweetHandler {

	//Methods
	
	/**
	 * Prints out all tweets held in a array of tweets.
	 * @param tweetsIn Tweet Object to print
	 * @param stream Stream to print to.
	 */
	public void printTweets(Tweet[] tweetsIn, PrintStream stream)
	{
		stream.println("Printing tweets");
		for(Tweet tweet : tweetsIn)
			stream.println(tweet.toString());
	}

    /**
     * Gets a tweet by id.
     *
     * @author Rick Humes
     * @param tweetsIn Tweet Object to search.
     * @param tweetID Tweet ID to find.
     * @return The requested tweet. May be <code>null</code>.
     * @see Tweet
     */
    public Tweet getTweet(Tweet[] tweetsIn, String tweetID)
    {
    	Tweet tweet_return = null;
    	if(tweetsIn != null) {
	    	for (Tweet tweet : tweetsIn) {
	    		if(tweet.getID().equals(tweetID)) {
	    			tweet_return = tweet;
	    		}
	    	}
    	}
		return tweet_return;
    }
	
    /**
     * Gets tweet(s) by date range.
     *
     * @author Rick Humes
     * @param tweetsIn Tweet Object to search.
     * @param beginDate The earliest tweet able to be shown.
     * @param endDate The latest tweet able to be shown.
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(Tweet[] tweetsIn, Date beginDate, Date endDate)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweetsIn != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : tweetsIn)
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
     * @param tweetsIn Tweet Object to search.
     * @param search Text to be searched for.
     * @param case_sensitive The search is case sensitive?
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(Tweet[] tweetsIn, String search, boolean case_sensitive)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweetsIn != null)
    		{
    			if(!case_sensitive)
    				search = search.toLowerCase();
    			
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
        		
	    		for (Tweet tweet : tweetsIn)
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
	
    /**
     *  Organizes an array of tweets by date. Possibility to overwrite inputed array.
     * @param tweetsIn Tweet Object to organize
     * @param overwriteInput Overwrite this inputed array?
     * @return Returns the organized array of tweets.
     */
    public Tweet[] organizeByDate(Tweet[] tweetsIn, boolean overwriteInput)
    {
    	Tweet[] tweets = (overwriteInput) ? tweetsIn : tweetsIn.clone();
    	Arrays.sort(tweets, new ChronologicalTweet());
    	return tweets;
    }
	
    /**
     * Organizes an array of tweets by alphabetical order. Possibility to overwrite inputed array.
     * 
     * @param tweetsIn Tweet Object to organize
     * @param overwriteInput Overwrite this inputed array?
     * @return Returns the organized array of tweets.
     */
    public Tweet[] organizeByAlpha(Tweet[] tweetsIn, boolean overwriteInput)
    {
    	Tweet[] tweets = (overwriteInput) ? tweetsIn : tweetsIn.clone();
       	Arrays.sort(tweets, new AlphabeticallyTweet());
       	return tweets;
    }
    
}
