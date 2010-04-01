package Timelines;
import Exceptions.*;
import Twitter.Tweet;
import Twitter.Tweeter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * User_Timeline is what stores a users Tweets and timeline information for a search query.
 * <br /> It is the object that will be displaying in the GUI.
 * 
 * @author Scott Smiesko
 * @version 2/27/2010
 *
 */
public class UserTimeline extends Timeline{
	
	//Class Variables
	/**
	 * The url that points towards usertimeline via twitter... left open ended
	 */
	private final String userTimlineURL = "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=";
	
	private Tweeter tweeter = null;
	
	private Document timelineXML = null;
	
	//Class Constructor
	
	public UserTimeline(String newTweeter)
	{
		System.out.println("Creating User Timeline for " + newTweeter);
		try {
			tweeter = new Tweeter(newTweeter);
		} catch (TweeterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh();
	}
	
	//Class Methods

	/**
	 * Sets the users unique Timeline URL
	 * @param newTimelineURL
	 */
	private void downloadXML() throws TweeterException
	{
		if(tweeter.isProtected())
			throw new UserProtectedException(tweeter);
		
		System.out.println("Downloading User Timeline.");

		try 
		{
			timelineXML = new SAXBuilder().build(new URL(userTimlineURL + tweeter.getUserID()));
		}
		catch(JDOMException e) {}
		catch (IOException e)
		{
			System.out.println(tweeter.getScreenName() + "'s tweets seem to be locked!");
			timelineXML = null;
		}
	}
	
	/**
	 * Method to populate our ArrayList of tweets with the tweets from a user
	 */
	private void parseXML()
	{
		System.out.println("Parsing XML");
		
		try
		{
			List<Element> statuses = timelineXML.getRootElement().getChildren("status");
			ArrayList<Tweet> temp = new ArrayList<Tweet>();
			
			for(Element element : statuses)
			{
				String tweetID = element.getChildText("id");
				String tweetText = element.getChildText("text");
				String tweetSource = element.getChildText("source");
				String tweetDate = element.getChildText("created_at");
				
				Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetSource);
				addFeedItem(tweet);
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
	
}
