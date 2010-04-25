package Timelines;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Changes.Timeline;
import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;


public class UserTimeline extends Timeline{
	
	private Document timelineXML 		= null;
	private Tweeter tweeter 			= null;
	//private ArrayList<Tweet> userTweets = null;
	
	//Class Constructor
	
	public UserTimeline(Tweeter newTweeter) throws TweeterException
	{
		tweeter = newTweeter;
		System.out.println("Creating User Timeline for " + tweeter.getUserName());
		downloadXML();
		parseXML();
	}
	



	private void downloadXML() throws TweeterException
	{
		
		System.out.println("Downloading User Timeline.");

		timelineXML = XMLHelper.getTweetsByScreenName(tweeter.getUserName());
		
	}
	

	private void parseXML()
	{
		System.out.println("Parsing UserTimeline XML");
		
		//userTweets = new ArrayList<Tweet>();
	
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
			// do REGEX on tweetMethod string to get past <a blahblah> GOOD STUFF HERE </a>
			
			date = (Element)status.getElementsByTagName("created_at").item(0);
			String tweetDate = date.getTextContent();
			
			Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetMethod);
		
			addDisplayItem(tweet);		}
		
	}
	
	public void update()
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

	
}
