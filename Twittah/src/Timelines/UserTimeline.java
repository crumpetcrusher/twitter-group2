package Timelines;

//Import Statements
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
import Changes.Timeline;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;


//The UserTimeline class!
public class UserTimeline extends Timeline {
    
    //Stores the tweeter who this timeline belongs to.
    private Tweeter tweeter = null;
    
    //Thread that will download and parse xml seperately from the main thread
    Thread thread = (new Thread() {
        public void run() {
                downloadXML();
                if(timelineXML != null)
                    parseXML();
                timelineRefreshed();
                suspend();
                }
        });
    
    //This constructor allows for the creation of a UserTimeline via parseFromDocument
    private UserTimeline(){}
    
    
    //This constructor allows you to pass in a tweeter and get his/her timeline
    public UserTimeline(Tweeter newTweeter)
    {
        tweeter = newTweeter;
        downloadAndParse();
    }
    
    //Returns a UserTimeline based off of a document
    public static UserTimeline parseFromDocument(Document doc)
    {
        UserTimeline temp = new UserTimeline();
        temp.timelineXML = doc;
        temp.parseXML();
        return temp;
    }
    
    //Downloads the XML neccesary for parsing
    private void downloadXML()
    {
        timelineXML = XMLHelper.getTweetsByScreenName(tweeter.text());
    }
    
    //Parses the XML passed in
    @SuppressWarnings("deprecation")
    private void parseXML()
    {
        Element statuses, status, text, method, date, tweeterName, tweeterIcon, tweeterEle;
        NodeList allStatuses;
        
        statuses = (timelineXML.getDocumentElement());
        allStatuses = statuses.getElementsByTagName("status");
        
        for (int t = 0; t < allStatuses.getLength(); ++t)
        {
            status = (Element) (allStatuses.item(t));
            
            //Creates a tweeter if there is not one
            if(tweeter == null)
            {
                tweeterEle = (Element) status.getElementsByTagName("user").item(0);
                tweeterName = (Element) tweeterEle.getElementsByTagName("name").item(0);
                tweeterIcon = (Element) tweeterEle.getElementsByTagName("profile_image_url").item(0);
                try 
                {
                    tweeter = new Tweeter(tweeterName.getTextContent(), new ImageIcon(new URL(tweeterIcon.getTextContent())), this);
                    while(tweeter == null) {}
                }
                catch (DOMException e) 
                {
                    e.printStackTrace();
                }
                catch (MalformedURLException e) 
                {
                    e.printStackTrace();
                }
            }

            text = (Element) status.getElementsByTagName("text").item(0);
            String tweetText = text.getTextContent();

            method = (Element) status.getElementsByTagName("source").item(0);
            String tweetMethod = method.getTextContent();
            
            //Replaces any HTML tags
            Matcher matcher = Pattern.compile("</?.*?>").matcher(tweetMethod);
            while (matcher.find()) {
                    tweetMethod = matcher.replaceAll("");
            }

            date = (Element) status.getElementsByTagName("created_at").item(0);
            String tweetDate = date.getTextContent();
            
            Tweet tweet = new Tweet(tweeter, tweetText, new Date(tweetDate), tweetMethod);

            addDisplayItem(tweet);
        }
        
    }

    //Downloads and Parses the user's timeline
    public void downloadAndParse() 
    {
        thread.start();
    }

    //Saves the user's timeline to a local XML file
    @Override
    public void saveTimeline() 
    {
        XMLHelper.writeDocument(timelineXML, "src/usertimeline_" + tweeter.text() + ".xml");
    }
	
}
