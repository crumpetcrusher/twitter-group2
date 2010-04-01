package SortingClasses;

import java.util.Date;
import RandomClasses.FeedItem;

/**
 * This class allows for sorting chronologically using the .sort method.
 * @author rah323
 */
public class ChronologicalFeedItem implements FeedComparer {
	
	//Class Variables
	
	/**
	 * Variable that stores whether or not the tweets should be sorted descending or ascending.
	 */
	private boolean descending = true;
	
	//Constructors
	
	/**
	 * Constructor allows for no inputs. Automatically does descending order.
	 */
	public ChronologicalFeedItem()
	{
	}
	
	/**
	 * Constructor that accepts an boolean whether or not to do descending..
	 * 
	 * @param isDescending Descending = true, Ascending = false;
	 */
	public ChronologicalFeedItem(boolean isDescending)
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
		Date feedItemDate 		= feedItem.date();
		Date anotherfeedItemDate = anotherfeedItem.date();
		
		sortInt = feedItemDate.compareTo(anotherfeedItemDate);
		
		if(!descending)
			sortInt *= -1;
		
		return sortInt;
	}

}
