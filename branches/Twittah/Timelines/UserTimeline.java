package Timelines;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;


public class UserTimeline{
	
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

		timelineXML = XMLHelper.getTweetsByUserID(tweeter.getUserID());
		
	}
	

	private void parseXML()
	{
		System.out.println("Parsing UserTimeline XML");
		
		userTweets = new ArrayList<Tweet>();
	
		Element statuses;
		Element status;
		Element id;
		Element text;
		Element method;
		Element date;
		
		NodeList allStatuses;
		
		statuses = (Element)(timelineXML.getDocumentElement());
		
		allStatuses = statuses.getElementsByTagName("status");
		
		for (int t=0; t < allStatuses.getLength(); ++t)
		{
			status = (Element)(allStatuses.item(t));
			
			id = (Element)status.getElementsByTagName("id").item(0);
			String tweetID = id.getTextContent();
			
			text = (Element)status.getElementsByTagName("text").item(0);
			String tweetText = text.getTextContent();
			
			method = (Element)status.getElementsByTagName("source").item(0);
			String tweetMethod = method.getTextContent();
			
			date = (Element)status.getElementsByTagName("created_at").item(0);
			String tweetDate = date.getTextContent();
			
			Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetMethod);
		
			userTweets.add(tweet);
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
