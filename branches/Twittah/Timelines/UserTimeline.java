package Timelines;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;


public class UserTimeline{
	

	private final String userTimlineURL = "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=";
	private Document timelineXML 		= null;
	private Tweeter tweeter 			= null;
	private ArrayList<Tweet> userTweets = null;
	
	//Class Constructor
	
	public UserTimeline(Tweeter newTweeter)
	{
		tweeter = newTweeter;
		System.out.println("Creating User Timeline for " + tweeter.getUserID());
		refresh();
	}
	



	private void downloadXML() throws TweeterException
	{
		
		System.out.println("Downloading User Timeline.");

		try 
		{
			timelineXML = new SAXBuilder().build(new URL(userTimlineURL + tweeter.getUserID()));
		}
		catch(JDOMException e) {}
		catch (IOException e)
		{
			System.out.println(tweeter.getUserID() + "'s tweets seem to be locked!");
			timelineXML = null;
		}
	}
	

	private void parseXML()
	{
		System.out.println("Parsing UserTimeline XML");
	
		try
		{
			List<Element> statuses = timelineXML.getRootElement().getChildren("status");
			userTweets = new ArrayList<Tweet>();
			
			for(Element element : statuses)
			{
				String tweetID = element.getChildText("id");
				String tweetText = element.getChildText("text");
				String tweetSource = element.getChildText("source");
				String tweetDate = element.getChildText("created_at");
				
				Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetSource);
				userTweets.add(tweet);
			}
		}
		catch(NullPointerException e)
		{
		}
	}
	
	public void refresh()
	{
		try
		{
			downloadXML();
			parseXML();
		}
		catch(TweeterException e) 
		{
			System.out.println("Unable to refresh.");
		}
	}
	
	public ArrayList<Tweet> getUserTweets()
	{
		return userTweets;
	}
	
}
