package Timelines;

import java.util.ArrayList;

import Twitter.Tweet;

public class CompositeTimeline {

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Attributes
	//
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Constructors
	//
	public CompositeTimeline()
	{
		
	}
	
	public CompositeTimeline(ArrayList<Tweet> fillTweets)
	{
		for(Tweet tweet : fillTweets)
		{
			tweets.add(tweet);
		}
	}
	
	public void add(ArrayList<Tweet> fillTweets)
	{
		for(Tweet tweet : fillTweets)
		{
			tweets.add(tweet);
		}
	}
	
	public ArrayList<Tweet> getTweets()
	{
		return tweets;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Class Methods
	//
	
/*
	public void organizeByDate()
	{
		Collections.sort(tweets, new ChronologicalTweets());
	}
	
	public void organizeByText()
	{
		Collections.sort(tweets, new AlphabeticallyTweets());
	}
	
	public void organizeBySource(){}
*/

	
}
