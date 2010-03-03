	package SortingClasses;

import java.util.Comparator;
import java.util.Date;
import Twitter.Tweet;

@SuppressWarnings("unchecked")
public class ChronologicalTweet implements Comparator {

	private boolean descending = true;
	
	public ChronologicalTweet()
	{
	}
	
	public ChronologicalTweet(boolean isDescending)
	{
		descending = isDescending;
	}
	
	public int compare(Object tweet, Object anotherTweet)
	{
		int sortInt;
		Date tweetText 		= ((Tweet)tweet).getDate();
		Date anotherTweetText = ((Tweet)anotherTweet).getDate();
		
		sortInt = tweetText.compareTo(anotherTweetText);
		
		if(!descending)
			sortInt = (sortInt == 0) ? 1 : 0;
		
		return sortInt;
	}

}
