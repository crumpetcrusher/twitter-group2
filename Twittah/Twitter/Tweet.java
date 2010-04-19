package Twitter;
import java.util.*;

import Changes.DisplayItem;


/**
 * This is the tweet object.
 */
public class Tweet implements DisplayItem
{
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Attributes
	//
	private Tweeter tweeter 	= null;
	/**
	 * {@code tweetID} - Unique ID number of the tweet
	 */
    private String tweetID 		= null;
    /**
     * {@code tweetDate} Date in which the tweet was posted
     */
    private Date tweetDate 		= null;
    /**
     * {@code tweetText} Message that was posted
     */
    private String tweetText 	= null;
    /**
     * {@code tweetMethod} - Method used to post the tweet
     */
    private String tweetMethod 	= null;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Constructors
	//
    /**
     * This is a constructor method that will take a full tweet input.
     * @param newID The unique ID of the tweet.
     * @param newText Message of the tweet.
     * @param newDate The date the tweet was posted.
     * @param newSource The source from which the tweet came.
     */
    public Tweet(Tweeter newTweeter, String newID, String newText, Date newDate, String newSource)
    {
    	tweeter = newTweeter;
    	tweetID = newID;
    	tweetText = newText;
    	tweetDate= newDate;
    	tweetMethod = newSource;
    }
    
    /**
     * This is a constructor method that will take a tweet object
     * @param tweet - tweet object
     */
    public Tweet(Tweet tweet)
    {
    	this(tweet.tweeter, tweet.tweetID, tweet.tweetText, tweet.tweetDate, tweet.tweetMethod);
    }
    
    
    
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Methods
	//
    public Tweeter getTweeter()
    {
    	return tweeter;
    }
    /**
     * This will get the ID of the tweet
     * @return ID The unique ID of the tweet.
     */
    public String getID()
    {
    	return tweetID;
    }
    
    /**
     * This will get the date the tweet was posted.
     * @return Date Returns that date in which this tweet was posted.
     * @see Date
     */
    public Date getDate()
    {
        return tweetDate;
    }

    /**
     * This will get the text of the tweet.
     * @return text The text of this tweet.
     */
    public String getText()
    {
        return tweetText;
    }
   
    /**
     * This will get the method used to post the tweet.
     * @return source The source from which this tweet came.
     */
    public String getMethod()
    {
    	return tweetMethod;
    }

    /**
     * This will clone the tweet for a duplicate tweet object
     * @return Tweet - tweet object that has been cloned
     */
    public Tweet clone()
    {
    	return new Tweet(this);
    }

    
    //Added By Rick Humes 
    //April 29, 2010
    //Implementing DisplayItem for TweetViewer (to be renamed DisplayViewer)
    
    @Override
	public Date date() {
    	return tweetDate;
	}

	@Override
	public String source() {
		return tweetMethod;
	}

	@Override
	public String text() {
		 return tweetText;
	}

	@Override
	public Tweeter tweeter() {
		return tweeter;
	}
    
 
}