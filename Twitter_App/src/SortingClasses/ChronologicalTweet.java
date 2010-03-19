package SortingClasses;

import java.util.Date;
import Twitter.Tweet;

/**
 * This class allows for sorting chronologically using the .sort method.
 * @author rah323
 */
public class ChronologicalTweet implements TweetComparer {
	
	//Class Variables
	
	/**
	 * Variable that stores whether or not the tweets should be sorted descending or ascending.
	 */
	private boolean descending = true;
	
	//Constructors
	
	/**
	 * Constructor allows for no inputs. Automatically does descending order.
	 */
	public ChronologicalTweet()
	{
	}
	
	/**
	 * Constructor that accepts an boolean whether or not to do descending..
	 * 
	 * @param isDescending Descending = true, Ascending = false;
	 */
	public ChronologicalTweet(boolean isDescending)
	{
		descending = isDescending;
	}
	
	//Methods
	
	/**
	 * This method is used specifically by Comparator class extended in TweetComparer
	 * @param tweet 
	 * @param anotherTweet 
	 * @return <font color=red>Used only by the Comparator class</font>
	 */
	public int compare(Tweet tweet, Tweet anotherTweet)
	{
		int sortInt;
		Date tweetDate 		= tweet.getDate();
		Date anotherTweetDate = anotherTweet.getDate();
		
		sortInt = tweetDate.compareTo(anotherTweetDate);
		
		if(!descending)
			sortInt *= -1;
		
		return sortInt;
	}

}
