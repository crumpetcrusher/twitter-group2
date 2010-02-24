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
	public static final Tweet Test_Tweet_1 = new Tweet(Tweeter.Test_Tweeter_1, 1, "Join me for tea, please.", new Date(2010,0,16,1,41), Source.IPhone);
	public static final Tweet Test_Tweet_2 = new Tweet(Tweeter.Test_Tweeter_1, 2, "The tea is on the table!", new Date(2010,0,16,2,23), Source.IPhone);
	public static final Tweet Test_Tweet_3 = new Tweet(Tweeter.Test_Tweeter_1, 3, "No one showed up for tea :(", new Date(2010,0,16,4,10), Source.Tweet_Deck);

    private Tweeter person = null;			//Stores the person as a user (see User class for user variables)
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
    public Tweet(Tweeter person, int id, String text, Date date, Source source)
    {
    	try
    	{
    		this.setPerson(person);
    		this.setID(id);
    		this.setText(text);
    		this.setDate(date);
    		this.setSource(source);
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
     * This gets the person who posted this tweet.
     *
     * @author Rick Humes
     * @return person Returns the person who posted this tweet.
     */
    public Tweeter getPerson()
    {
        return person;
    }
    
    /**
     * This sets the person who posted this tweet.
     *
     * @author Rick Humes
     * @param person The user that posted the tweet.
     * @see Tweeter
     * @return boolean Returns whether or not the user was set successfully.
     */
    public boolean setPerson(Tweeter person)
    {
    	boolean value = false;
    	try
    	{
	        this.person = person;
	        //There may be some reason to validate the inputed object.
	        value = true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return value;
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
    public boolean setID(int id)
    {
    	boolean value = false;
    	try
    	{
	        this.id = id;
	        value = true;
		}
		catch(Exception e) 
		{ 
			System.out.println(e.getMessage()); 
		}
		
		return value;
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
        return this.date;
    }

    /**
     * This will set the date the tweet was posted.
     *
     * @author Rick Humes
     * @param date Sets the date in which the tweet was posted.
     * @see Date
     * @return boolean Returns whether or not the date was set successfully.
     */
    public boolean setDate(Date date)
    {
    	boolean value = false;
    	try
    	{
	        this.date = date;
	        value = true;
		}
		catch(Exception e) 
		{ 
			System.out.println(e.getMessage()); 
		}
		
		return value;
    }

    /**
     * This will get the body of this tweet.
     * 
     * @author Rick Humes
     * @return text The text of this tweet.
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * This will set the body of the tweet.
     *
     * @author Rick Humes
     * @param text The body of the tweet.
     * @return boolean Returns whether or not the body was set successfully.
     */
    public boolean setText(String text)
    {
    	boolean value = false;
    	try
    	{
	        this.text = text;
	        //There may be some reason to validate the inputed object.
	        value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }
   
    /**
     * This will get the source of this tweet.
     * 
     * @author Rick Humes
     * @return source The source from which this tweet came.
     */
    public Source getSource()
    {
    	return this.source;
    }
    
    /**
     * This will set the source of the tweet.
     *
     * @author Rick Humes
     * @param source The source from which this tweet came.
     * @return boolean Returns whether or not the source was set successfully.
     */
    public boolean setSource(Source source)
    {
    	boolean value = false;
    	try
    	{
	        this.source = source;
	        //There may be some reason to validate the inputed object.
	       	value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }
    
    /**
     * This returns the text.
     * 
     * @author Rick Humes
     * @return title The title of the tweet.
     */
    public String toString()
    {
        return this.text;
    }
}