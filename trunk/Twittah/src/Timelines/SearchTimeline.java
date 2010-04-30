package Timelines;


//Import Statements
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Twitter.Tweet;
import Twitter.Tweeter;
import backend.XMLHelper;

public class SearchTimeline extends Timeline {

//Class Variables
    private String query;
    private Thread thread = (new Thread() {
        public void run() {
            do
            {
                downloadXML();
                if(timelineXML != null)
                    parseXML();
                timelineRefreshed();
                suspend();
            }while(true);
        }});
    
    
//Class Constructors
    
    //Constructor that allows this object to create itself without having to download the XML
    private SearchTimeline() {}
    
    
    //Constructor that allows for a query to be made
    public SearchTimeline(String newQuery)
    {
        query = newQuery;
        downloadAndParse();
    }

//Class Methods
    
    //Returns a SearchTimeline based off a document
    public static SearchTimeline parseFromDocument(Document doc, String queryIn)
    {
            SearchTimeline temp = new SearchTimeline();
            temp.query = queryIn;
            temp.timelineXML = doc;
            temp.parseXML();
            return temp;
    }
    
    //Downloads the XML for the timeline
    private void downloadXML()
    {
        timelineXML = XMLHelper.getTweetsByKeywords(query);
    }
    
    //Parses the XML document
    private void parseXML()
    {
        if(timelineXML != null)
        {
            Element entries;
            Element entry;
            Element id;
            Element text;
            Element method;
            Element date;
            Tweeter tweeter;
            Matcher matcher;
            
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
                
                matcher = Pattern.compile("</?.*?>").matcher(tweetMethod);
                while (matcher.find())
                        tweetMethod = matcher.replaceAll("");
                
                date = (Element)entry.getElementsByTagName("published").item(0);
                String tweetDate = date.getTextContent();
                
                //Gets the username without the junk
                matcher = Pattern.compile("T.+").matcher(tweetDate);
                tweetDate = matcher.replaceAll("");
                
                //Gets the date without the junk
                matcher = Pattern.compile("-").matcher(tweetDate);
                tweetDate = matcher.replaceAll("/");
                
                Tweet tweet = new Tweet(tweeter, tweetText, new Date(tweetDate), tweetMethod);
    
                addDisplayItem(tweet);
            }
        }
    }

    //Downloads and parses the XML document
    @Override
    public void downloadAndParse() 
    {
        if(!thread.isAlive())
            thread.start();
        thread.resume();
    }

    //Saves the timeline locally
    @Override
    public void saveTimeline() 
    {
        XMLHelper.writeDocument(timelineXML, "src/searchtimeline_" + query + ".xml");  
    }
    
}