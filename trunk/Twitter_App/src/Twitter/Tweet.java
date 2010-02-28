package Twitter;
import java.util.*;

/**
 * This is the tweet object.<br />
 * @version 2/27/2010 @ 7:01PM - Updated documentation & clarified attribute names
 * <br />Changed tweetID to type String; the only times I think we'll be using it is when we're appending it to a URL or a user is searching..

 * @author Rick Humes
 * @author Scott Smiesko
 */
public class Tweet
{
	/**
	 * {@code tweetID} - Unique ID number of the tweet
	 */
    private String tweetID 	= null;
    /**
     * {@code tweetDate} Date in which the tweet was posted
     */
    private Date tweetDate 	= null;
    /**
     * {@code tweetText} Message that was posted
     */
    private String tweetText 	= null;
    /**
     * {@code tweetSource} - Method used to post the tweet
     */
    private String tweetSource = null;
    
    /**
     * This is a constructor method that will take a full tweet input.
     *
     * @author Rick Humes
     * @param newID The unique ID of the tweet.
     * @param newText Message of the tweet.
     * @param newDate The date the tweet was posted.
     * @param newSource The source from which the tweet came.
     */
    public Tweet(String newID, String newText, Date newDate, String newSource)
    {
    		setID(newID);
    		setText(newText);
    		setDate(newDate);
    		setSource(newSource);
    }
   
    /**
     * Sole constructor. Use this if you wish to add information later.
     */
    public Tweet()
    {
    }
    
    /**
     * @author Rick Humes
     * @return ID The unique ID of the tweet.
     */
    public String getID()
    {
    	return tweetID;
    }
    
    /**
     * This will set the unique id of this tweet.
     *
     * @author Rick Humes
     * @param newID The unique id of the tweet.
     * @see Date
     */
    public void setID(String newID)
    {
	    tweetID = newID;
    }
    
    /**
     * This will get the date the tweet was posted.
     *
     * @author Rick Humes
     * @return Date Returns that date in which this tweet was posted.
     * @see Date
     */
    public Date getDate()
    {
        return tweetDate;
    }

    /**
     * This will set the date the tweet was posted.
     *
     * @author Rick Humes
     * @param newDate Sets the date in which the tweet was posted.
     * @see Date
     */
    public void setDate(Date newDate)
    {
	   tweetDate = newDate;
    }

    /**
     * This will get the body of this tweet.
     * 
     * @author Rick Humes
     * @return text The text of this tweet.
     */
    public String getText()
    {
        return tweetText;
    }

    /**
     * This will set the body of the tweet.
     *
     * @author Rick Humes
     * @param newText The body of the tweet.
     */
    public void setText(String newText)
    {
    	tweetText = newText;
    }
   
    /**
     * This will get the source of this tweet.
     * 
     * @author Rick Humes
     * @return source The source from which this tweet came.
     */
    public String getSource()
    {
    	return tweetSource;
    }
    
    /**
     * This will set the source of the tweet.
     *
     * @author Rick Humes
     * @param newSource The source from which this tweet came.
     */
    public void setSource(String newSource)
    {
		tweetSource = newSource;
    }
    
    /**
     * toString for showing the tweet information
     * @return finalTweet - our string showing all the attributes of a Tweet
     */
    public String toString() {
    	
    	String finalTweet;
    	
    	finalTweet = "Text: " + tweetText + ", Date: " + tweetDate + ", Source: " + tweetSource + ", ID: " + tweetID;
    	
    	return finalTweet;
    }
}