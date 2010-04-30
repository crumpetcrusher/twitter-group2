//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : DisplayItemViewer
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class is an extended JPanel used for displaying the actual tweet information to the user. It takes in
// a DisplayItem which has all the necessary information for displaying a tweet and adds it to the panel. The
// UTC date is parsed to show the time like Facebook and Twitter, like "7 minutes ago" and "about 3 months ago".
// 
//
// KNOWN LIMITATIONS
// JTextArea has no method for setting vertical text alignment. Text displayed is displayed at the top of the
// component, making text squished together.
// 
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Changes.DisplayItem;

public class DisplayItemViewer extends JPanel {

    // There are four components used to show the individual tweet information to the user.
    //
    // Username                 : A JLabel to show the Username of the tweeter.
    //
    // Picture                  : A JLabel to show an ImageIcon of the users profile picture.
    //
    // Text                     : A JTextArea that shows the 140 characters of a tweet.
    //
    // metadata                 : A JLabel used to show the date and method used to post the tweet.
    //
    private static final long serialVersionUID = 4089145031957417967L;
    JLabel                    Username         = null;
    JLabel                    Picture          = null;
    JTextArea                 Text             = new JTextArea();
    JLabel                    metadata         = null;

    // This is the constructor for the DisplayItemViewer class.
    //
    // item : The specific DisplayItem that is to be shown inside our JPanel.
    //
    public DisplayItemViewer(DisplayItem item) {
        
        // Typical size our JPanel should be for displaying the DisplayItem.
        //
        setPreferredSize(new Dimension(400, 100));

        // Pass the date the item was posted to twitterHumanFriendlyDate() which returns a string "about x ago"
        // so the user has a better sense of when it was posted.
        //
        // Also, Concatenate the date and method used to post the tweet into one metadata string so it looks
        // like: "4 hours ago via Seesmic", just like twitters' web interface.
        //
        String date = twitterHumanFriendlyDate(item.date().toString());
        String source = " via " + item.source().toString();
        String metaString = date + source;
        
        // Add the user information to their respective JLabels.
        //
        Username = new JLabel(item.owner());
        Picture = new JLabel(item.icon());
        metadata = new JLabel(metaString);
        Text.setText(item.text());
        Text.setOpaque(false);
        Text.setLineWrap(true);
        Text.setWrapStyleWord(true);
        Text.setEditable(false);

        // Set a BorderLayout for this JPanel and add the information to their respective areas in the layout.
        //
        setLayout(new BorderLayout());
        add(Username, BorderLayout.NORTH);
        add(Picture, BorderLayout.WEST);
        add(Text, BorderLayout.CENTER);
        add(metadata, BorderLayout.SOUTH);
    }

    // This operation converts the UTC date string from a displayItems' date() method into a
    // human-friendly date format (eg. 4 hours ago, 5 days ago, yesterday.)
    //	
    // It is passed a date string in UTC and returns the string relating to the duration that has passed since
    // the tweet was posted.
    //
    public String twitterHumanFriendlyDate(String dateStr) {

        // Get the date created by creating a SimpleDateFormat string that represents how twitter 
        // displays their date strings and parse the actual tweet date into a Date object.
        //
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss z yyyy");
        dateFormat.setLenient(false);
        Date created = null;
        try {
            created = dateFormat.parse(dateStr);
        }
        catch (Exception e) {
            return null;
        }

        // Get todays date and create a duration in milliseconds from the start of EPOCH time
        // this is how we will know how long it has passed since now.. just like how long time has passed
        // from EPOCH start to the duration.  
        //
        Date today = new Date();
        Long duration = today.getTime() - created.getTime();

        // Some constant numbers in milliseconds for comparing against the duration
        //
        long second = 1000;
        long minute = second * 60;
        long hour   = minute * 60;
        long day    = hour * 24;
        long week   = day * 7;
        long month  = (week * 4) + (day * 2);
        long year  = month * 12;

        // Gentlemen, start your extremely long IF statements..
        // 
        if (duration < second * 7) {
            return "right now";
        }
        else if ((duration > second * 7) && (duration < minute)) {
            int n = (int) Math.floor(duration / second);
            return n + " seconds ago";
        }
        else if ((duration > minute) && (duration < minute * 2)) {
            return "about 1 minute ago";
        }
        else if ((duration > minute * 2) && (duration < hour)) {
            int n = (int) Math.floor(duration / minute);
            return "about " + n + " minutes ago";
        }
        if ((duration > hour) && (duration < hour * 2)) {
            return "about 1 hour ago";
        }
        if ((duration > hour * 2) && (duration < day)) {
            int n = (int) Math.floor(duration / hour);
            return "about " + n + " hours ago";
        }
        if ((duration > day) && (duration < day * 2)) {
            return "yesterday";
        }
        if ((duration > day * 2) && (duration < week)) {
            int n = (int) Math.floor(duration / day);
            return "about " + n + " days ago";
        }
        if ((duration > week) && (duration < week * 2)) {
            return "about a week ago";
        }
        if ((duration > week * 2) && (duration < month)) {
            int n = (int) Math.floor(duration / week);
            return "about " + n + " weeks ago";
        }
        if ((duration > month) && (duration < month * 2)) {
            return "about a month ago";
        }
        if ((duration > month * 2) && (duration < year)) {
            int n = (int) Math.floor(duration / month);
            return "about " + n + " months ago";
        }
        if ((duration > year) && (duration < year * 2)) {
            return "about a year ago";
        }
        if (duration > year * 2) {
            int n = (int) Math.floor(duration / year);
            return "about " + n + " years ago";
        }
        else {
            return "back in my day...";
        }
    }
}
