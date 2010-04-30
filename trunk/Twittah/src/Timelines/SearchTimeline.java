//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : SearchTimeline
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This is a extention of Timeline which will store a timeline of tweets we searched for.  Given a query, it 
// downloads and parses the XML Atom feed and adds all the found matches to its list of tweet objects. 
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Timelines;

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

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    private String query;
    private Thread thread = (new Thread() {
                              public void run() {
                                  do {
                                      downloadXML();
                                      if (timelineXML != null)
                                          parseXML();
                                      timelineRefreshed();
                                      suspend();
                                  }
                                  while (true);
                              }
                          });

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //

    // Constructor that allows this object to create itself without having to download the XML
    // 
    private SearchTimeline() {}

    // Constructor that allows for a query to be made
    //
    public SearchTimeline(String newQuery) {
        query = newQuery;
        downloadAndParse();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //

    // Returns a SearchTimeline based off a document
    //
    public static SearchTimeline parseFromDocument(Document doc, String queryIn) {
        SearchTimeline temp = new SearchTimeline();
        temp.query = queryIn;
        temp.timelineXML = doc;
        temp.parseXML();
        return temp;
    }

    // Downloads the XML for the timeline
    //
    private void downloadXML() {
        timelineXML = XMLHelper.getTweetsByKeywords(query);
    }

    // Parses the XML document
    //
    private void parseXML() {
        if (timelineXML != null) {
            Element entries;
            Element entry;
            Element id;
            Element text;
            Element method;
            Element date;
            Tweeter tweeter;
            Matcher matcher;

            NodeList allEntries;

            entries = (Element) (timelineXML.getDocumentElement());

            allEntries = entries.getElementsByTagName("entry");

            // Iterate through the list of entries to get each match for a search
            //
            for (int t = 0; t < allEntries.getLength(); ++t) {
                entry = (Element) (allEntries.item(t));

                // Get the name of the tweeter
                //
                id = (Element) entry.getElementsByTagName("name").item(0);
                String tweetID = id.getTextContent();
                tweeter = new Tweeter(tweetID, null, null);

                // Get the text of the tweet we're searching for
                //
                text = (Element) entry.getElementsByTagName("title").item(0);
                String tweetText = text.getTextContent();

                // Get the source method of the tweet
                // 
                method = (Element) entry.getElementsByTagName("twitter:source")
                        .item(0);
                String tweetMethod = method.getTextContent();

                // Replace HTML in the method so the method is an actual title and not HTML code
                //
                matcher = Pattern.compile("</?.*?>").matcher(tweetMethod);
                while (matcher.find())
                    tweetMethod = matcher.replaceAll("");

                // Get the date the tweet was posted
                //
                date = (Element) entry.getElementsByTagName("published")
                        .item(0);
                String tweetDate = date.getTextContent();

                // Gets the username without the junk
                //
                matcher = Pattern.compile("T.+").matcher(tweetDate);
                tweetDate = matcher.replaceAll("");

                // Gets the date without the junk
                //
                matcher = Pattern.compile("-").matcher(tweetDate);
                tweetDate = matcher.replaceAll("/");

                Tweet tweet = new Tweet(tweeter, tweetText, new Date(tweetDate), tweetMethod);

                addDisplayItem(tweet);
            }
        }
    }

    // Downloads and parses the XML document
    // 
    @Override
    public void downloadAndParse() {
        if (!thread.isAlive())
            thread.start();
        thread.resume();
    }

    // Saves the timeline locally
    // 
    @Override
    public void saveTimeline() {
        XMLHelper.writeDocument(timelineXML, "src/searchtimeline_" + query
                + ".xml");
    }

}
