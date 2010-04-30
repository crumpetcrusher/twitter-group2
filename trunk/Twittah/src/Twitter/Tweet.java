//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : Tweet
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class holds the information for a specific tweet.  It is constructed by passing the tweeter who posted
// the tweet, the text content of the tweet, the date the tweet was posted, and the method of posting the tweet.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Twitter;

import java.util.Date;
import javax.swing.ImageIcon;
import Changes.DisplayItem;

public class Tweet implements DisplayItem {

    // This class has 4 components used to store information about a tweet:
    //
    // tweeter          : The tweeter object who posted the tweet.
    //
    // tweetDate        : The date string in UTC for when the tweet was posted.
    //
    // tweetText        : The 140 character text content of the tweet.
    //
    // tweetMethod      : The method used to post the tweet (like Seesmic)
    //
    //
    private Tweeter tweeter     = null;
    private Date    tweetDate   = null;
    private String  tweetText   = null;
    private String  tweetMethod = null;

    // This class constructor allows for creation of a tweet, passing in all data.
    //
    public Tweet(Tweeter newTweeter, String newText, Date newDate, String newMethod) {
        
        // Set our variables with the incoming information
        //
        tweeter = newTweeter;
        tweetText = newText;
        tweetDate = newDate;
        tweetMethod = newMethod;
    }
    
    
    // The following methods override the interface DisplayItem 
    //

    // Returns the date at which this tweet was tweeted
    //
    @Override
    public Date date() {
        return tweetDate;
    }

    // Returns the method by which the tweet was made
    //
    @Override
    public String source() {
        return tweetMethod;
    }

    // Returns the text of the tweet
    //
    @Override
    public String text() {
        return tweetText;
    }

    // Returns the tweeter's icon
    //
    @Override
    public ImageIcon icon() {
        return tweeter.icon();
    }

    // Returns the user's screen name
    //
    @Override
    public String owner() {
        return tweeter.text();
    }
}
