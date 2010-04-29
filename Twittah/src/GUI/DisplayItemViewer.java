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

    //
    //
    JLabel    Username = null;
    JLabel    Picture  = null;
    JTextArea Text     = new JTextArea();
    JLabel    metadata = null;

    // This is the constructor for the DisplayItemViewer class
    //
    // item : The specific DisplayItem that is to be shown inside our JPanel.
    //
    public DisplayItemViewer(DisplayItem item) {
        // Typical size our JPanel should be for displaying the DisplayItem
        setPreferredSize(new Dimension(400, 100));

        // 
        Text.setText(item.text());
        Text.setOpaque(false);
        Text.setLineWrap(true);
        Text.setWrapStyleWord(true);
        Text.setEditable(false);

        Username = new JLabel(item.text());
        Picture = new JLabel(item.icon());

        String date = twitterHumanFriendlyDate(item.date().toString());

        String source = " via " + item.source().toString();

        String metaString = date + source;

        metadata = new JLabel(metaString);

        setLayout(new BorderLayout());

        add(Username, BorderLayout.NORTH);
        add(Picture, BorderLayout.WEST);
        add(Text, BorderLayout.CENTER);
        add(metadata, BorderLayout.SOUTH);
    }

    // This operation converts the UTC date string, from a displayItems' date();
    // method, into a
    // human-friendly date format (eg. 4 hours ago, 5 days ago, yesterday)
    //	
    // It is passed a date string and returns the string relating to the
    // duration that has passed.
    //
    public String twitterHumanFriendlyDate(String dateStr) {

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

        Date today = new Date();

        Long duration = today.getTime() - created.getTime();

        long second = 1000;
        long minute = second * 60;
        long hour = minute * 60;
        long day = hour * 24;
        long week = day * 7;
        long month = (week * 4) + (day * 2);
        long year = month * 12;

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
