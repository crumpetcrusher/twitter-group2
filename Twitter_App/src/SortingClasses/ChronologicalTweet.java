	package SortingClasses;

import java.util.Comparator;
import java.util.Date;
import Twitter.Tweet;

@SuppressWarnings("unchecked")
public class ChronologicalTweet implements TweetComparer {

	private boolean descending = true;
	
	public ChronologicalTweet()
	{
	}
	
	public ChronologicalTweet(boolean isDescending)
	{
		descending = isDescending;
	}
	
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
