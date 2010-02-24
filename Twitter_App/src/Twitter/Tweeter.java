package Twitter;
import java.util.*;
import java.awt.image.BufferedImage;

/**
 * This is the user object.
 * <dl>
 * <dt>Pre-defined Objects:
 * 		<dd>{@link Tweeter#Test_Tweeter_1 Test_User_1}
 * </dl>
 * 
 * @author Rick
 * @version 2/2/2009
 * 
 */
public class Tweeter
{
	
	//Pre-defined Class
	
	/**
	 * This is a test Tweeter for testing.
	 * <dl>
	 * <dt>Pre-defined Variables:
	 * 		<dd>{@link Tweeter#id id} = 123456
	 * 		<dd>{@link Tweeter#user_name user_name} = "Hatta"
	 *  	<dd>{@link Tweeter#name name} = "The Mad Hatter"
	 *  	<dd>{@link Tweeter#picture picture} = null
	 * </dl>
	 */
	public static final Tweeter Test_Tweeter_1 = new Tweeter(123456, "Hatta", "The Mad Hatter", null);
	
	//Class Variables
	
    /**
     * Stores the users unique id.
     */
    private int id = -1;
    /**
     * Stores the users user name
     */
    private String user_name = null;
    /**
     * Stores the name of the user
     */
    private String name = null;
    /**
     * Stores the tweeter's profile picture.
     */
    private BufferedImage picture = null;
    /**
     * Stores the tweeter's tweets.
     */
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    //Constructors
    
    /**
     * This is a constructor method that will take all user inputs.
     *
     * @author Rick Humes
     * @param userID Person's unique id.
     * @param userName Person's username.
     * @param name Person's name.
     * @param picture Person's profile picture.
     */
    public Tweeter(int userID, String userName, String name, BufferedImage picture)
    {
    	try
    	{
	    	this.setUserID(userID);
	    	this.setUserName(userName);
	    	this.setName(name);
	    	this.setPicture(picture);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
    /**
     * Sole constructor. Use this if you wish to add information later.
     */
    public Tweeter()
    {
    }

    //Methods
    
    /**
     * This gets the person's unique id.
     *
     * @author Rick Humes
     * @return userID Person's unique id.
     */
    public int getUserID()
    {
        return this.id;
    }

    /**
     * This sets the person's unique id.
     *
     * @author Rick Humes
     * @param userID Peron's unique id.
     * @return boolean Whether or not the unique id was set successfully.
     */
    public boolean setUserID(int userID)
    {
    	boolean value = false;
    	try
    	{
	        this.id = userID;
	        value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }
    
    /**
     * This gets the person's username
     *
     * @author Rick Humes
     * @return userName Person's username
     */
    public String getUserName()
    {
        return this.user_name;
    }

    /**
     * This sets the person's username.
     *
     * @author Rick Humes
     * @param userName Peron's username.
     * @return boolean Whether or not the username was set successfully.
     */
    public boolean setUserName(String userName)
    {
    	boolean value = false;
    	try
    	{
	        this.user_name = userName;
	        value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }

    /**
     * This gets the person's name
     *
     * @author Rick Humes
     * @return name Person's name.
     */
	public String getName() {
		return name;
	}
	
    /**
     * This sets the person's name.
     *
     * @author Rick Humes
     * @param name Peron's name.
     * @return boolean Whether or not the name was set successfully.
     */
    public boolean setName(String name) 
    {
    	boolean value = false;
    	try
    	{
    		this.name = name;
    		value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
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
        return this.picture;
    }

    /**
     * This sets the person's profile picture.
     *
     * @author Rick Humes
     * @param picture Peron's profile picture.
     * @return boolean Whether or not the profile picture was set successfully.
     */
    public boolean setPicture(BufferedImage picture)
    {
    	boolean value = false;
    	try
    	{
	        this.picture = picture;
	        value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }
    
    /**
     * This adds a tweet to this persons tweets.
     *
     * @author Rick Humes
     * @param tweet A tweet.
     * @see Tweet
     * @return boolean Whether or not the tweet was added successfully.
     */
    public boolean addTweet(Tweet tweet)
    {
    	boolean value = false;
    	try
    	{
    		tweets.add(tweet);
    		value = true;
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		return value;
    }
    
    /**

     * Gets a tweet by id.
     *
     * @author Rick Humes
     * @param id The id of the tweet.
     * @return The requested tweet. May be <code>null</code>.
     * @see Tweet
     */
    public Tweet getTweet(int id)
    {
    	Tweet tweet_return = null;
    	try
    	{
    		if(tweets != null)
	    		for (Tweet tweet : tweets)
	    			if(tweet.getID() == id)
	    				tweet_return = tweet;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return tweet_return;
    }
     
    /**
     * Gets tweet(s) by sources.
     *
     * @author Rick Humes
     * @param sources The sources from which the tweet(s) came.
     * @see Source
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(Source[] sources)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweets != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : tweets)
	    			for(Source source : sources)
		    			if(tweet.getSource() == source)
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
     * Gets tweet(s) by source.
     *
     * @author Rick Humes
     * @param source The source from which the tweet(s) came.
     * @see Source
     * @return The requested tweet(s). May be <code>null</code>.
     * @see Tweet
     */
    public Tweet[] getTweets(Source source)
    {
    	Tweet[] tweet_return = null;
    	try
    	{
    		if(tweets != null)
    		{
        		ArrayList<Tweet> requestedTweets = new ArrayList<Tweet>();
	    		for (Tweet tweet : tweets)
	    			if(tweet.getSource() == source)
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
    
    /**
     * This returns the person's username
     * 
     * @author Rick Humes
     * @return userName Peron's username.
     */
    public String toString()
    {
        return this.user_name;
    }
}