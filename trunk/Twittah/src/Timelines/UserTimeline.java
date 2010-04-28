package Timelines;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Changes.DisplayItem;
import Changes.Timeline;
import Exceptions.TweeterException;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;

public class UserTimeline extends Timeline {

	private Tweeter tweeter = null;
	Thread thread = null;

	private UserTimeline()
	{
		
	}
	
	public UserTimeline(Tweeter newTweeter) throws TweeterException {
		tweeter = newTweeter;
		System.out.println("Creating User Timeline for "
				+ tweeter.getUserName());
		downloadAndParse();
	}


	public String toString()
	{
		return tweeter.getUserName();
	}
	private void downloadXML() throws TweeterException {
		System.out.println("Downloading User Timeline.");
		timelineXML = XMLHelper.getTweetsByScreenName(tweeter.getUserName());
	}
	
	public static UserTimeline parseFromDocument(Document doc)
	{
		UserTimeline temp = new UserTimeline();
		temp.timelineXML = doc;
		temp.parseXML();
		return temp;
	}

	private void parseXML(){
		System.out.println("Parsing UserTimeline XML");

		Element statuses;
		Element status;
		Element id;
		Element text;
		Element method;
		Element date;
		Element tweeterName;
		Element tweeterIcon;
		Element tweeterEle;

		NodeList allStatuses;

		statuses = (timelineXML.getDocumentElement());

		allStatuses = statuses.getElementsByTagName("status");

		for (int t = 0; t < allStatuses.getLength(); ++t) {
			status = (Element) (allStatuses.item(t));

			if(tweeter == null)
			{
				tweeterEle = (Element) status.getElementsByTagName("user").item(0);
				tweeterName = (Element) tweeterEle.getElementsByTagName("name").item(0);
				tweeterIcon = (Element) tweeterEle.getElementsByTagName("profile_image_url").item(0);
				try {
					tweeter = new Tweeter(tweeterName.getTextContent(), new ImageIcon(new URL(tweeterIcon.getTextContent())), this);
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			id = (Element) status.getElementsByTagName("id").item(0);
			String tweetID = id.getTextContent();

			text = (Element) status.getElementsByTagName("text").item(0);
			String tweetText = text.getTextContent();

			String pattern = "</?.*?>";
			method = (Element) status.getElementsByTagName("source").item(0);
			String tweetMethod = method.getTextContent();
			
			Matcher matcher = Pattern.compile(pattern).matcher(tweetMethod);
			while (matcher.find()) {
				tweetMethod = matcher.replaceAll("");
			}

			date = (Element) status.getElementsByTagName("created_at").item(0);
			String tweetDate = date.getTextContent();
			
			Tweet tweet = new Tweet(tweeter, tweetID, tweetText, new Date(tweetDate), tweetMethod);

			addDisplayItem(tweet);
		}
	}

	public void downloadAndParse() {
        thread = (new Thread() {
            public void run() {
                    do {
                            try {
                    			downloadXML();
                    			if(timelineXML != null)
                    				parseXML();
                    			suspend();
                            } catch (TweeterException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            
                    } while (isAlive());
            }
    });
        	thread.start();
	}
	/*
	@Override
	public Element toElement(Document doc) {
		Element temp = doc.createElement("Timeline");
		Element tweets = doc.createElement("Tweets");
		temp.setAttribute("Type", "User");
		temp.setTextContent(tweeter.getUserName());
		
		for(DisplayItem item : displayItems)
			tweets.appendChild(item.toElement(doc));
		
		temp.appendChild(tweets);
		return temp;
	}*/

	@Override
	public void saveTimeline() {
		XMLHelper.writeDocument(timelineXML, "src/usertimeline_" + tweeter.getUserName() + ".xml");
		
	}
	
}
