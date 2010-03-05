package SortingClasses;

import java.util.Comparator;

import Twitter.Tweet;

/**
 * This class is for the sort method.
 * @author Rick Humes
 *
 */
@SuppressWarnings("unchecked")
public class AlphabeticallyTweet implements TweetComparer {

	
	//Class Variables
	/**
	 * Whether or not to sort by descending.
	 */
	private boolean descending = true;
	
	//Constructors
	
	/**
	 * Constructor allows for no inputs. Automatically does descending order.
	 */
	public AlphabeticallyTweet()
	{
	}
	
	/**
	 * Constructor allows for the user to decide to order by ascending or descending.
	 * 
	 * @param isDescending Descending = true, Ascending = false;
	 */
	public AlphabeticallyTweet(boolean isDescending)
	{
		descending = isDescending;
	}
	
	//Methods
	
	/**
	 * This method is an override in the Comparator class 
	 * 
	 * @param tweet
	 * @param anotherTweet
	 * @return Return is only needed for the comparator
	 */
	public int compare(Tweet tweet, Tweet anotherTweet)
	{
		int sortInt;
		String tweetText 		= tweet.getText();
		String anotherTweetText = anotherTweet.getText();
		
		sortInt = tweetText.compareTo(anotherTweetText);
		
		if(!descending)
			sortInt *= -1;
		
		return sortInt;
			
			
			
	}
}
