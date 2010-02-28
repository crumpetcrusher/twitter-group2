package Timelines;
import Twitter.Tweet;
import java.util.*;

/**
 * User_Timeline is what stores a users Tweets and timeline information for a search query.
 * <br /> It is the object that will be displaying in the GUI.
 * 
 * @author Scott Smiesko
 * @version 2/27/2010
 *
 */
public class User_Timeline {

	/**
	 * {@code localTimelineXML} - The URL of the users timeline
	 */
	private	String localTimelineXML = null;
	/**
	 * {@code userID} - The unique ID of the user
	 */
	private	String userID = null;
	/**
	 * {@code sinceTweetID} - The Tweet ID for only showing a users tweets with an ID greater than sinceTweetID
	 */
	private	String sinceTweetID = null;
	/**
	 * {@code maxTweetID} - The Tweet ID for only showing a users tweets with an ID less than or equal to maxTweetID
	 */
	private	String maxTweetID = null;
	/**
	 * {@code count} - How many of a users tweets to display in their timeline
	 */
	private	int	count = -1;
	/**
	 * {@code pageOfTweets} - The specific page number of a users tweets to display
	 */
	private	String pageOfTweets = null;
	/**
	 * {@code tweets} - An ArrayList of Tweet objects
	 */
	private	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	/**
	 * Default Constructor for populating a User_Timeline object
	 * @param newUserID
	 */
	public User_Timeline(String newUserID) {
		setUserID(newUserID);
		String newTimelineURL = "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=" + userID;
		getTimeline(newTimelineURL);
		populateTweets();
	}
	
	/**
	 * Sets the users unique ID number
	 * @param newUserID
	 */
	private void setUserID(String newUserID)
	{
		userID = newUserID;	
	}

	/**
	 * Sets the users unique Timeline URL
	 * @param newTimelineURL
	 */
	public void getTimeline(String newTimelineURL)
	{
		
		// TODO: Implement saving XML from Twitters servers 
		
		localTimelineXML = null;
	}
	
	/**
	 * Method to populate our ArrayList of tweets with the tweets from a user
	 */
	private void populateTweets()
	{

		
		// TODO: Parse the XML file we saved to disk
		
		// TODO: Get index count for our tweets array 
		//			(needed for showing all tweets in array)
		
		/*
		String tweetID 		= null;
		String tweetText 	= null;
		Date   tweetDate 	= null;
		String tweetSource 	= null;
		
		tweets.get(i).setID(tweetID);
		tweets.get(i).setText(tweetText);
		tweets.get(i).setDate(tweetDate);
		tweets.get(i).setSource(tweetSource);
		*/
	}
	
}
