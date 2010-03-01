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
	
	//Class Variables
	
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
    
    //Class Constructor
    
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
    	tweetID = newID;
    	tweetText = newText;
    	tweetDate= newDate;
    	tweetSource = newSource;
    }
    
    //Class Methods

     /**
     * @author Rick Humes
     * @return ID The unique ID of the tweet.
     */
    public String getID()
    {
    	return tweetID;
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
     * toString for showing the tweet information
     * @return finalTweet - our string showing all the attributes of a Tweet
     */
    public String toString() {
    	
    	String finalTweet;
    	
    	finalTweet = 	"[Tweet Object]" 	+ 					"\n\t" +
    					"Text: " 			+ 	tweetText 	+ 	"\n\t" + 
    					"Date: " 			+ 	tweetDate 	+ 	"\n\t" + 
    					"Source: " 			+ 	tweetSource + 	"\n\t" + 
    					"ID: " 				+ 	tweetID;
    	
    	return finalTweet;
    }
}