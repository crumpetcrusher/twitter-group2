package Timelines;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
		private String query;
		Thread thread = null;
		
		public SearchTimeline(String newQuery)
		{
			System.out.println("Creating Search Timeline for " + newQuery);
			query = newQuery;
			reload();
		}
		
		private SearchTimeline() {
			
		}
		
		/**
		 * Sets the users unique Timeline URL
		 * @param newTimelineURL
		 */
		private void downloadXML() throws TweeterException
		{
			System.out.println("Downloading Search " + query + " Timeline.");
			
			timelineXML = XMLHelper.getTweetsByKeywords(query);
		}
		
		/**
		 * Method to populate our ArrayList of tweets with the tweets from a user
		 */
		private void parseXML()
		{
			
			System.out.println("Parsing Search XML");
		
		
			Element entries;
			Element entry;
			Element id;
			Element text;
			Element method;
			Element date;
			Element tweeterEle;
			Tweeter tweeter;
            Pattern pattern; //= Pattern.compile(console.readLine("%nEnter your regex: "));
            Matcher matcher; //= pattern.matcher(console.readLine("Enter input string to search: "));
			
			NodeList allEntries;
			
			entries = (Element)(timelineXML.getDocumentElement());
			
			allEntries = entries.getElementsByTagName("entry");
			
			for (int t=0; t < allEntries.getLength(); ++t)
			{
				entry = (Element)(allEntries.item(t));
				
				id = (Element)entry.getElementsByTagName("name").item(0);
				String tweetID = id.getTextContent();
				tweeter = new Tweeter(tweetID, null, null);
				
				text = (Element)entry.getElementsByTagName("title").item(0);
				String tweetText = text.getTextContent();
				
				method = (Element)entry.getElementsByTagName("twitter:source").item(0);
				String tweetMethod = method.getTextContent();
				String methodPattern = "</?.*?>";
				Matcher methodMatcher = Pattern.compile(methodPattern).matcher(tweetMethod);
				while (methodMatcher.find()) {
					tweetMethod = methodMatcher.replaceAll("");
				}
				
				date = (Element)entry.getElementsByTagName("published").item(0);
				String tweetDate = date.getTextContent();
				pattern = Pattern.compile("T.+");
				matcher = pattern.matcher(tweetDate);
				tweetDate = matcher.replaceAll("");
				pattern = Pattern.compile("-");
				matcher = pattern.matcher(tweetDate);
				tweetDate = matcher.replaceAll("/");
				Date date1 = new Date(tweetDate);
				
				
				Tweet tweet = new Tweet(tweeter, tweetID, tweetText, date1, tweetMethod);//new Date(tweetDate), tweetMethod);
				
				//System.out.println(tweet);
				addDisplayItem(tweet);
			}
		}
		
		public void reload()
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

		@Override
		public void downloadAndParse() {
		        thread = (new Thread() {
		            public void run() {
		                            try {
		                                        downloadXML();
		                                        if(timelineXML != null)
		                                                parseXML();
		                                        timelineRefreshed();
		                                        suspend();
		                            } catch (TweeterException e) {
		                                                                // TODO Auto-generated catch block
		                                                                e.printStackTrace();
		                                                        }
		            }
		    });
		                thread.start();
		}

		@Override
		public void saveTimeline() {
			XMLHelper.writeDocument(timelineXML, "src/searchtimeline_" + query + ".xml");
			
		}
		
		public static SearchTimeline parseFromDocument(Document doc)
		{
			SearchTimeline temp = new SearchTimeline();
			temp.timelineXML = doc;
			temp.parseXML();
			return temp;
		}
		

}
