//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Class Name           : UserTimeline
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This class is the storage location for a users timeline.  It parses either a URL or XML document passed into
// it and with its extension of Timeline allows for the tweets to be stored within the userTimeline.  
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;

public class UserTimeline extends Timeline {
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class has 1 component used to store timelines.
    //
    // tweeter        : Stores the tweeter of whos timeline this is for.
    // 
    // thread         : A thread that is created to do the downloading and parsing of an XML document alongside 
    //                  other program functionality so that other events can continue without this completely
    //                  bringing the program to a halt.
    //
    //
    private Tweeter tweeter = null;
    Thread thread           = (new Thread() {
        public void run() {
            do
            {
                downloadXML();
                if(timelineXML != null)
                    parseXML();
                suspend();
            }while(true);
        }
        });
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // This constructor allows for the creation of a UserTimeline via parseFromDocument
    //
    private UserTimeline(){} 
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // Returns a UserTimeline based off of a XML document
    //
    public static UserTimeline parseFromDocument(Tweeter tweeter, Document doc)
    {
        UserTimeline temp = new UserTimeline();
        temp.tweeter = tweeter;
        while(temp.tweeter.icon() == null){}
        temp.timelineXML = doc;
        temp.parseXML();
        return temp;
    }
    
    // Downloads the XML neccesary for parsing
    // 
    private void downloadXML()
    {
        timelineXML = XMLHelper.getTweetsByScreenName(tweeter.text());
    }
    
    // Parses the XML passed in
    // 
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
            //
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
            
            // Replaces HTML tags in the method used
            //
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

    // Downloads and Parses the user's timeline, starting the thread defined above
    //
    public void downloadAndParse() 
    {
        if(!thread.isAlive())
            thread.start();
        thread.resume();
    }

    // Saves the user's timeline to a local XML file
    //
    @Override
    public void saveTimeline() 
    {
        XMLHelper.writeDocument(timelineXML, "src/usertimeline_" + tweeter.text() + ".xml");
    }
	
}
