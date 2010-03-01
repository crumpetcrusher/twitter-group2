package SortingClasses;

import java.util.Comparator;

import Twitter.Tweet;

public class AlphabeticallyTweet implements Comparator{

	private boolean descending = true;
	
	public AlphabeticallyTweet()
	{
	}
	
	public AlphabeticallyTweet(boolean isDescending)
	{
		descending = isDescending;
	}
	
	public int compare(Object tweet, Object anotherTweet)
	{
		int sortInt;
		String tweetText 		= ((Tweet)tweet).getText();
		String anotherTweetText = ((Tweet)anotherTweet).getText();
		
		sortInt = tweetText.compareTo(anotherTweetText);
		
		if(!descending)
			sortInt = (sortInt == 0) ? 1 : 0;
		
		return sortInt;
			
			
			
	}
}
