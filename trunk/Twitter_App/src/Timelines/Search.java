package Timelines;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;

public class Search extends Timeline {

	private final String searchURL = "http://search.twitter.com/search.atom?q=";
	private String query;
	private Document timelineXML = null;
	
	
	public Search(String newQuery)
	{
		System.out.println("Creating Search Timeline for " + newQuery);
		query = newQuery;
		refresh();
	}
	
	/**
	 * Sets the users unique Timeline URL
	 * @param newTimelineURL
	 */
	private void downloadXML() throws TweeterException
	{
		
		System.out.println("Downloading Search Timeline.");

		try 
		{
			timelineXML = new SAXBuilder().build(new URL(searchURL + query));
		}
		catch(Exception e){
			System.out.println("Error downloading XML!");
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
			List<Element> statuses = timelineXML.getRootElement().getChildren("entry");
			ArrayList<Tweet> temp = new ArrayList<Tweet>();
			
			for(Element element : statuses)
			{
				
				String tweetID = element.getChildText("id");
				String tweetText = element.getChildText("content");
				String tweetSource = element.getChildText("twitter:source");
				String tweetDate = element.getChildText("published");
				String user = element.getChildText("link");
				Pattern pattern = Pattern.compile("(?<=twitter.com\\/).+?(?=\\/statuses)");
				Matcher matcher = pattern.matcher(user);
	            while (matcher.find()) {
					user = "\"%s\"";
	            }

				Tweeter tweeter;
				System.out.println("hm3");
				try {
					System.out.println("AhHhH");
					tweeter = new Tweeter(user);
					Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetSource);
					addFeedItem(tweet);
				} catch (TweeterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
