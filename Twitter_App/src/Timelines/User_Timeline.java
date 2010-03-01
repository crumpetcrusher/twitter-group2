package Timelines;
import Twitter.Tweet;
import Twitter.Tweeter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * User_Timeline is what stores a users Tweets and timeline information for a search query.
 * <br /> It is the object that will be displaying in the GUI.
 * 
 * @author Scott Smiesko
 * @version 2/27/2010
 *
 */
public class User_Timeline {
	
	//Class Variables


	/**
	 * {@code tweets} - An ArrayList of Tweet objects
	 */
	private	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	/**
	 * The url that points towards usertimeline via twitter... left open ended
	 */
	private final String userTimlineURL = "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=";
	
	//Class Constructor
	
	/**
	 * Default Constructor for populating a User_Timeline object
	 * @param newUserID
	 */
	public User_Timeline(String newUserID) {
		populateTweets(getTimeline(userTimlineURL + newUserID));
	}
	
	//Class Methods

	/**
	 * Sets the users unique Timeline URL
	 * @param newTimelineURL
	 */
	private Document getTimeline(String newTimelineURL)
	{
		System.out.println("Retreiving user tweets...");

		Document doc = null;
		try 
		{
			doc = new SAXBuilder().build(new URL(newTimelineURL));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			doc = null;
		}
		
		return doc;
	}
	
	/**
	 * Method to populate our ArrayList of tweets with the tweets from a user
	 */
	private void populateTweets(Document document)
	{
		
		System.out.println("Populating tweets from received XML");

		ArrayList result = new ArrayList();
		List<Element> statuses = document.getRootElement().getChildren("status");
		
		for(Element element : statuses)
		{
			String tweetID = element.getChildText("id");
			String tweetText = element.getChildText("text");
			String tweetSource = element.getChildText("source");
			String tweetDate = element.getChildText("created_at");
			
			Tweet tweet = new Tweet(tweetID,tweetText, new Date(tweetDate), tweetSource);
			tweets.add(tweet);
		}
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
    	return (Tweet[])tweets.toArray();
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
