package Timelines;

import java.util.ArrayList;
import Twitter.Tweet;

/**
 * This is the timeline object.
 * 
 * @author Rick
 * @version 2/2/2009
 */
public class Timeline {
	
	//Class variables
	
	/**
	 * Stores the Tweets in the specified time line.
	 * @see Tweet
	 */
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	//Constructors
	
	/**
	 * This is the constructor.
	 * @author Rick Humes
	 */
	public Timeline()
	{
	}
	
    //Methods
	
	/**
	 * This method will allow you to access the tweets inside this object.
	 * 
	 * @author Rick Humes
	 * @return <b>ArrayList&#60Tweet&#62</b>The tweets in this timeline.
	 */
	public ArrayList<Tweet> get_Tweets() {
		return tweets;
	}
	
}
