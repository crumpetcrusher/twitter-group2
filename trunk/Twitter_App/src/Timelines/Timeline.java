package Timelines;

import java.util.ArrayList;

import Twitter.Tweet;

import Exceptions.Exception_MustBeGreaterThan;
import Exceptions.Exception_MustBeLessThan;
import Exceptions.Exception_NegativeNumber;

/**
 * This is the timeline object.
 * 
 * @author Rick
 * @version 2/2/2009
 */
public class Timeline {
	
	//Class variables
	
	/**
	 * Stores the type of time line to be used.
	 * 
	 * @see Timeline_Type
	 */
	@SuppressWarnings("unused")
	/**
	 * Stores the Tweets in the specified time line.
	 * @see Tweet
	 */
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	/**
	 * Stores the earliest ID to be displayed
	 */
	private int since_id;
	/**
	 * Stores the latest ID to be displayed.
	 */
	private int max_id;
	/**
	 * Stores the number of statuses to retrieve. (Max: 200)
	 */
	private int count;
	/**
	 * Stores the page to be displayed.
	 * @see <a href="http://apiwiki.twitter.com/Things-Every-Developer-Should-Know#6Therearepaginationlimits"> Page Limitations</a>
	 */
	private int page;
	
	//Constructors
	
	/**
	 * This is a constructor method that will take a Timeline_Type input.
	 * <dl>
	 * <dt>Example Inputs:
	 * 		<dl>type
	 * 			<dd>{@link Timeline_Type#_public Timline_Type._public}
	 * 		</dl>
	 * <dt>Available Inputs:
	 * 		<dl>type
	 * 			<dd>{@link Timeline_Type Timeline_Type}
	 * 		</dl>
	 * </dl>
	 * @author Rick Humes
	 * @param type Type of time line to be viewed
	 */
	protected Timeline()
	{
		since_id = 0;
		max_id = 0;
		since_id = 0;
		page = 0;
	}
	
	/**
	 * This is a constructor method that will take all class inputs.
	 * <dl>
	 * <dt>Example Inputs:
	 * 		<dl>type
	 * 			<dd>{@link Timeline_Type#_public Timline_Type._public}
	 * 		</dl>
	 * <dt>Available Inputs:
	 * 		<dl>type
	 * 			<dd>{@link Timeline_Type Timeline_Type}
	 * 		</dl>
	 * </dl>
	 * @author Rick Humes
	 * @param since_id The earliest tweet to be displayed.
	 * @param max_id The latest tweet to be displayed.
	 * @param count The amount of tweets to be displayed.
	 * @param page The page (if more than one) to be displayed.
	 */
	protected Timeline(int since_id, int max_id, int count, int page)
	{
		this();
		set_since_id(since_id);
		set_max_id(max_id);
		set_Page(page);
	}
	
    //Methods
	
	/**
	 * This method will set the tweets container to a new tweet container.
	 * </br>If you just want to add a tweet just use {@link #get_Tweets() get_Tweets().add(Tweet)}
	 * 
	 * @author Rick Humes
	 * @param tweets_input The tweets to be set.
	 * @return Whether or not the tweets inputed were set.
	 */
	public boolean set_Tweets(ArrayList<Tweet> tweets_input) {
    	boolean value = false;
    	try
    	{
	        tweets = tweets_input;
	        value = true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
	}

	/**
	 * This method will allow you to access the tweets inside this object.
	 * 
	 * @author Rick Humes
	 * @return <b>ArrayList&#60Tweet&#62</b>The tweets in this timeline.
	 */
	public ArrayList<Tweet> get_Tweets() {
		return tweets;
	}
	
	/**
	 * This method will set the earliest tweet to possibly display.
	 * 
	 * @author Rick Humes
	 * @param since_id_input The earliest tweet possible <b>Minimum: 0</b>
	 * @return <b>boolean</b> Whether or not the ID was set.
	 * @throws Exception_NegativeNumber 
	 */
	public boolean set_since_id(int since_id_input) throws Exception_NegativeNumber
	{
    	boolean value = false;
    	try
    	{
    		if(since_id < 0) throw new Exception_NegativeNumber();
		        since_id = since_id_input;
		        value = true;
    	}
    	catch(Exception_NegativeNumber negative)
    	{
    		negative.printStackTrace();
    	}
    	return value;
	}
	
	/**
	 * This method will give you the earliest id that can be displayed.
	 * 
	 * @author Rick Humes
	 * @return <b>int</b> The earliest id to display. 0 is default.
	 */
	public int get_since_id()
	{
		return this.since_id;
	}
	
	/**
	 * This method will set the latest tweet to be displayed.
	 * 
	 * @author Rick Humes
	 * @param max_id_input The latest tweet to be shown by ID.
	 * @return <b>boolean</b> Whether or not the value was set.
	 * @throws Exception_NegativeNumber 
	 */
	public boolean set_max_id(int max_id_input) throws Exception_NegativeNumber
	{
    	boolean value = false;
    	try
    	{
    		if(max_id_input < 0) throw new Exception_NegativeNumber();
	        max_id = max_id_input;
	        value = true;
    	}
    	catch(Exception_NegativeNumber negative)
    	{
    		negative.printStackTrace();
    	}
    	return value;
	}
	
	/**
	 * This method will give you the latest id that can be displayed.
	 * 
	 * @author Rick Humes
	 * @return <b>int</b> Returns the max_id
	 */
	public int get_max_id()
	{
		return this.since_id;
	}

	/**
	 * This method will set the max amount of tweets to display.
	 * 
	 * @author Rick Humes
	 * @param count_input Maximum amount of tweets to display. <b>Maximum: 200</b>
	 * @return <b>boolean</b> Whether or not the value was set.
	 * @throws Exception_MustBeLessThan
	 * @throws Exception_NegativeNumber 
	 */
	public boolean set_Count(int count_input) throws Exception_MustBeLessThan, Exception_NegativeNumber{	
    	boolean value = false;
    	try
    	{
    		if(count_input > 200) throw new Exception_MustBeLessThan(200);
    		if(count_input * get_Page() > 3200) throw new Exception_MustBeLessThan(3200);
    		if(count_input < 0) throw new Exception_NegativeNumber();
	        count = count_input;
	        value = true;
    	}
    	catch(Exception_MustBeLessThan lessThan)
    	{
    		lessThan.printStackTrace();
    	}
    	catch(Exception_NegativeNumber negative)
    	{
    		negative.printStackTrace();
    	}
    	return value;
	}

	/**
	 * This method will give you the maximum amount of tweets to be displayed.
	 * 
	 * @author Rick Humes
	 * @return <b>int</b> The maximum amount of tweets to be displayed.
	 */
	public int get_Count() {
		return count;
	}

	/**
	 * This method will set the page to display.
	 * 
	 * @author Rick Humes
	 * @param page_input Page displayed.
	 * @return <b>boolean</b> Whether or not the value was set.
	 * @throws Exception_MustBeLessThan
	 */
	public boolean set_Page(int page_input) throws Exception_MustBeLessThan, Exception_MustBeGreaterThan{
    	boolean value = false;
    	try
    	{
    		if(get_Count() * page_input > 3200) throw new Exception_MustBeLessThan(3200);
    		if(page_input < 0) throw new Exception_MustBeGreaterThan(-1);
    		page = page_input;
	        value = true;
    	}
    	catch(Exception_MustBeLessThan lessThan)
    	{
    		lessThan.printStackTrace();
    	}
    	catch(Exception_MustBeGreaterThan greaterThan)
    	{
    		greaterThan.printStackTrace();
    	}
    	return value;
	}

	/**
	 * This method will give you the page to be displayed.
	 * 
	 * @author Rick Humes
	 * @return <b>int</b> The page to be displayed.
	 */
	public int get_Page() {
		return page;
	}
	
	public boolean parseXML()
	{
		return false;
	}
}
