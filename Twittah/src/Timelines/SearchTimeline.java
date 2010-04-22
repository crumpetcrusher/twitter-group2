	package Timelines;

	import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Changes.Timeline;
import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;

public class SearchTimeline extends Timeline {

		private final String searchURL = "http://search.twitter.com/search.atom?q=";
		private String[] query;
		private Document timelineXML = null;
		private ArrayList<Tweet> tweets = null;
		
		
		public SearchTimeline(String newQuery)
		{
			System.out.println("Creating Search Timeline for " + newQuery);
			query = new String[1];
			query[0] = newQuery;
			update();
		}
		
		public SearchTimeline(String[] newQuery)
		{
			System.out.println("Creating Search Timeline for " + newQuery);
			query = newQuery;
			update();
		}
		
		/**
		 * Sets the users unique Timeline URL
		 * @param newTimelineURL
		 */
		private void downloadXML() throws TweeterException
		{
			System.out.println("Downloading User Timeline.");

			timelineXML = XMLHelper.getTweetsByKeywords(query);
		}
		
		/**
		 * Method to populate our ArrayList of tweets with the tweets from a user
		 */
		private void parseXML()
		{
			
			System.out.println("Parsing UserTimeline XML");
			
			tweets = new ArrayList<Tweet>();
		
			Element entries;
			Element entry;
			Element id;
			Element text;
			Element method;
			Element date;
			Element tweeterEle;
			Tweeter tweeter;
			
			NodeList allEntries;
			
			entries = (Element)(timelineXML.getDocumentElement());
			
			allEntries = entries.getElementsByTagName("entry");
			
			for (int t=0; t < allEntries.getLength(); ++t)
			{
				entry = (Element)(allEntries.item(t));
				
				id = (Element)entry.getElementsByTagName("uri").item(0);
				String tweetID = id.getTextContent();
				tweeter = new Tweeter(tweetID, null, null);
				
				text = (Element)entry.getElementsByTagName("content").item(0);
				String tweetText = text.getTextContent();
				
				method = (Element)entry.getElementsByTagName("twitter:source").item(0);
				String tweetMethod = method.getTextContent();
				// do REGEX on tweetMethod string to get past <a blahblah> GOOD STUFF HERE </a>
				
				date = (Element)entry.getElementsByTagName("published").item(0);
				String tweetDate = date.getTextContent();
				
				System.out.println(tweetID + " - " + tweetText + " - " + method + " - " + tweetDate);
				Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetMethod);//new Date(tweetDate), tweetMethod);
				
				System.out.println(tweet);
				tweets.add(tweet);
			}
			/*	
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
			}*/
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
		

		public ArrayList<Tweet> getSearchTweets() {
			
			return tweets;
		}

}
