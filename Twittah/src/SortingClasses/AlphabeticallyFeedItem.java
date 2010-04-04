package SortingClasses;

import RandomClasses.FeedItem;
import Twitter.Tweet;

/**
 * This class allows for sorting alphabetically using the .sort method.
 * @author rah323
 */
public class AlphabeticallyFeedItem implements FeedComparer {

	//Class Variables
	/**
	 * Variable that stores whether or not the tweets should be sorted descending or ascending.
	 */
	private boolean descending = true;
	
	//Constructors
	
	/**
	 * Constructor allows for no inputs. Automatically does descending order.
	 */
	public AlphabeticallyFeedItem()
	{
	}
	
	/**
	 * Constructor that accepts an boolean whether or not to do descending..
	 * 
	 * @param isDescending Descending = true, Ascending = false;
	 */
	public AlphabeticallyFeedItem(boolean isDescending)
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
	public int compare(FeedItem feedItem, FeedItem anotherfeedItem)
	{
		int sortInt;
		String feedItemText 		= feedItem.source();
		String anotherFeedItemText = anotherfeedItem.source();
		
		sortInt = feedItemText.compareTo(anotherFeedItemText);
		
		if(!descending)
			sortInt *= -1;
		
		return sortInt;
			
			
			
	}

}
