package Twitter;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.xpath.*;

import org.xml.sax.InputSource;


/**
 * This is the user object.
 * 
 * @author Rick
 * @version 2/2/2009
 * 
 * @author Scott Smiesko
 * @version 2/24/2009
 * 
 */
public class Tweeter
{
	//Class Variables
	
	/**
	 * Stores the users info XML feed url
	 */
	private String userXMLInfoURL = null;
	/**
	 * Stores the users timeline XML feed url
	 */
	private String userXMLTimelineURL = null;
    /**
     * Stores the users unique id.
     */
    private String userID = null;
    /**
     * Stores the real name of the user
     */
    private String realName = null;
    /**
     * Stores the users screen name
     */
    private String screenName = null;
    /**
     * Stores the users location
     */
    private String userLocation = null;
    /**
     * Stores the users description
     */
    private String userDescription = null;
    /**
     * Stores the users home page
     */
    private String userHomePage = null;
    /**
     * Stores the users verification status
     */
    private String verifiedUser = null;
    /**
     * Stores the tweeter's profile picture.
     */
    private BufferedImage userPicture = null;
    /**
     * Stores the tweeter's tweets.
     */
    private ArrayList<Tweet> userTweets = new ArrayList<Tweet>();

    //Constructors
    
    /**
     * Constructor method that requires a UserID.  
     *  uses the userID to get the twitter Info XML File of that user
     * 
     * @author Scott Smiesko
     * @param  newUserID - userID used to get twitter feed
     */
    public Tweeter(String newUserID)
    {
    	setUserID(newUserID);
    	setUserXMLInfoURL("http://api.twitter.com/1/users/show.xml?user_id=" + userID);
    	setUserXMLTimelineURL("http://api.twitter.com/1/statuses/user_timeline.xml?user_id=" + userID);
    	
    	populateTweeterFromXML(userXMLInfoURL);
    }
    

    // Methods

    /**
     * Populates the rest of a tweeter object with data from the XML feed
     * 
     * @author Scott Smiesko
     * @param xmlFile
     */
    public void populateTweeterFromXML(String xmlFile)
    {
    	
    	XPath xpath = XPathFactory.newInstance().newXPath();
    	InputSource source = new InputSource (xmlFile);
    	
    	System.out.println("Grabbing XML from Twitter.. be patient");
    	
    	try {
			String result = xpath.evaluate("/user/protected", source);
			if (result == "false")
			{
				System.out.println("User feed is locked, bummer");
			}
			else
			{
				/**
				 * Set the attributes for our user
				 */
				realName = xpath.evaluate("/user/name", source);
				screenName = xpath.evaluate("/user/screen_name", source);
				userLocation = xpath.evaluate("/user/location", source);
				userDescription = xpath.evaluate("/user/description", source);
				userPicture = ImageIO.read(new URL(xpath.evaluate("/user/profile_image_url", source)));
				userHomePage = xpath.evaluate("/user/url", source);
				verifiedUser = xpath.evaluate("/user/verified", source);
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			userPicture = null;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * This sets the URL for the XML feed with user information
     *
     * @author Scott Smiesko
     * @param newUserXMLInfoURL
     * 
     */
    public void setUserXMLInfoURL(String newUserXMLInfoURL)
    {
    	userXMLInfoURL= newUserXMLInfoURL; 
    }
    
    /**
     * This gets the URL for the XML feed with user information.
     *
     * @author Scott Smiesko
     * @return {@code userXMLFeed} - the URL to the users own XML feed
     */
    public String getUserXMLInfoURL()
    {
    	return userXMLInfoURL;
    }
    
    /**
     * This sets the URL for the XML feed with user tweets
     * @param newUserXMLTimelineURL
     */
    public void setUserXMLTimelineURL(String newUserXMLTimelineURL)
    {
    	userXMLTimelineURL = newUserXMLTimelineURL;
    }
    
    /**
     * This sets the URL for the XML feed with user timeline
     * @return userXMLTimelineURL;
     */
    
    public String getUserXMLTimelineURL()
    {
    	return userXMLTimelineURL;
    }
    
    /**
     * This gets the person's unique id.
     *
     * @author Rick Humes
     * @return userID Person's unique id.
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * This sets the person's unique id.
     *
     * @author Rick Humes
     * @param userID Peron's unique id.
     * @return boolean Whether or not the unique id was set successfully.
     */
    public void setUserID(String newUserID)
    {
    	userID = newUserID;
    }
    
    /**
     * This gets the person's username
     *
     * @author Rick Humes
     * @return userName Person's username
     */
    public String getScreenName()
    {
        return screenName;
    }

    /**
     * This sets the person's screenName.
     *
     * @author Rick Humes
     * @param userName Peron's username.
     * @return boolean Whether or not the username was set successfully.
     */
    public void setScreenName(String newScreenName)
    {
    	screenName = newScreenName;
    }

    /**
     * This gets the person's name
     *
     * @author Rick Humes
     * @return name Person's name.
     */
	public String getRealName() 
	{
		return realName;
	}
	
    /**
     * This sets the person's name.
     *
     * @author Rick Humes
     * @param name Peron's name.
     * @return boolean Whether or not the name was set successfully.
     */
    public void setRealName(String newRealName) 
    {
    	realName = newRealName;
	}

    /**
     * This gets the person's profile picture.
     *
     * @author Rick Humes
     * @return picture Peron's profile picture
     * @see BufferedImage
     */
    public BufferedImage getPicture()
    {
        return userPicture;
    }

    /**
     * This sets the person's profile picture.
     *
     * @author Rick Humes
     * @param picture Peron's profile picture.
     * @return boolean Whether or not the profile picture was set successfully.
     */
    public void setPicture(BufferedImage newUserPicture)
    {
    	userPicture = newUserPicture;
    }
    
    /**
     * This adds a tweet to this persons tweets.
     *
     * @author Rick Humes
     * @param tweet A tweet.
     * @see Tweet
     * @return boolean Whether or not the tweet was added successfully.
     */
    public void addTweet(Tweet newTweet)
    {

    	userTweets.add(newTweet);

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
    public ArrayList<Tweet> getTweets()
    {
    	return userTweets;
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
    	if(userTweets != null) {
	    	for (Tweet tweet : userTweets) {
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
    		if(userTweets != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : userTweets)
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
    		if(userTweets != null)
    		{
    			if(!case_sensitive)
    				search = search.toLowerCase();
    			
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
        		
	    		for (Tweet tweet : userTweets)
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
     * This returns all attributes of a Tweeter
     * 
     * @author Scott Smiesko
     * @return {@code userInfo} - appended string of all Tweeter attributes
     */
    public String toString()
    {
    	String userInfo = null;
    	userInfo = "Info: "				+ userXMLInfoURL + 		"\n" +
    			   "Timeline: " 		+ userXMLTimelineURL + 	"\n" +
    			   "ID: " 				+ userID + 				"\n" +
    			   "Real Name: " 		+ realName + 			"\n" +
    			   "Screen Name: "		+ screenName + 			"\n" +
    			   "Location: "			+ userLocation + 		"\n" +
    			   "Description: "		+ userDescription + 	"\n" +
    			   "Home Page: "		+ userHomePage + 		"\n" +
    			   "Verified?: "		+ verifiedUser + 		"\n" +
    			   "Picture: "			+ userPicture + 		"\n" +
    			   "Tweets: "			+ userTweets + 			"\n" ;
        return userInfo;
    }
}