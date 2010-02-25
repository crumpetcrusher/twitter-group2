package Twitter;
import java.util.*;

/**
 * This is the tweet object.
 * 
 * @author Rick
 * @version 1/16/2009
 */
public class Tweet
{

    private int id = -1;				//Stores the unique id of the tweet
    private Date date = null;			//Stores the date in which the tweet was created
    private String text = null;			//Stores the body of the tweet
    private Source source = null;		//Stores the source from which the tweet came

    /**
     * This is a constructor method that will take a full tweet input.
     *
     * @author Rick Humes
     * @param person The person that posted the tweet.
     * @see Tweeter 
     * @param id The unique id of the user.
     * @param text The text of the tweet.
     * @param date The date the tweet was posted.
     * @param source The source from which the tweet came
     */
    public Tweet(int id, String text, Date date, Source source)
    {
    	try
    	{
    		setID(id);
    		setText(text);
    		setDate(date);
    		setSource(source);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
   
    /**
     * Sole constructor. Use this if you wish to add information later.
     */
    public Tweet()
    {
    }
    
    /**
     * @author Rick Humes
     * @return id The unique id of the tweet.
     */
    public int getID()
    {
    	return id;
    }
    
    /**
     * This will set the unique id of this tweet.
     *
     * @author Rick Humes
     * @param id The unique id of the tweet.
     * @see Date
     * @return boolean Whether or not the id was set successfully.
     */
    public void setID(int newid)
    {
	    id = newid;
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
        return date;
    }

    /**
     * This will set the date the tweet was posted.
     *
     * @author Rick Humes
     * @param date Sets the date in which the tweet was posted.
     * @see Date
     * @return boolean Returns whether or not the date was set successfully.
     */
    public void setDate(Date newdate)
    {
	   date = newdate;
    }

    /**
     * This will get the body of this tweet.
     * 
     * @author Rick Humes
     * @return text The text of this tweet.
     */
    public String getText()
    {
        return text;
    }

    /**
     * This will set the body of the tweet.
     *
     * @author Rick Humes
     * @param text The body of the tweet.
     * @return boolean Returns whether or not the body was set successfully.
     */
    public void setText(String newtext)
    {
    	text = newtext;
    }
   
    /**
     * This will get the source of this tweet.
     * 
     * @author Rick Humes
     * @return source The source from which this tweet came.
     */
    public Source getSource()
    {
    	return source;
    }
    
    /**
     * This will set the source of the tweet.
     *
     * @author Rick Humes
     * @param source The source from which this tweet came.
     * @return boolean Returns whether or not the source was set successfully.
     */
    public void setSource(Source newSource)
    {
		source = newSource;
    }
    public String toString() {
    	
    	String finalTweet;
    	
    	finalTweet = "Text: " + text + ", Date: " + date + ", Source: " + source + ", ID: " + id;
    	
    	return finalTweet;
    }
}