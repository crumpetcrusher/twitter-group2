package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Changes.DisplayItem;

/**
 * TweetContainer is a JPanel containing all the information in a tweet that
 * gets displayed in a composite timeline
 * would have.
 * @author Frappe051
 *
 */
public class DisplayItemViewer extends JPanel
{

	/**
	 * 
	 */
	JLabel Username = null;
	/**
	 * 
	 */
	JLabel Picture = null;
	/**
	 * 
	 */
	JTextArea Text = new JTextArea();

	JLabel metadata = null;
	
	/**
	 * Constructor for creating a TweetContainer GUI object (in a JPanel) 
	 * 
	 * @param tweet
	 */
	
	public DisplayItemViewer(DisplayItem item)
	{
		setPreferredSize(new Dimension(400,100));
		
		Text.setText(item.text());
		Text.setOpaque(false);
		Text.setWrapStyleWord(true);
		
		Username = new JLabel(item.tweeter().getUserName());
		Picture = new JLabel(item.tweeter().getUserPicture());
		
		String date;
		long then = item.date().getTime();
		
		date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (then*1000));
		
		/* shitty code swearing is bad... mmmkay

		 
		if (years > 0) {
			if (years == 1) {
				date = "about " + years + " year ago ";
			}
			else {
				date = "about " + years + " years ago ";
			}
		}
		else if (months > 0) {
			if (months == 1) {
				date = "about " + months + " month ago ";
			}
			else {
				date = "about " + months + " months ago ";
			}
		}
		else if (weeks > 0){
			if (weeks == 1) {
				date = "about " + weeks + " week ago ";
			}
			else {
				date = "about " + weeks + " weeks ago ";
			}
		}
		else if (days > 0) {
			if (days == 1) {
				date = "about " + days + " day ago ";
			}
			else {
				date = "about " + days + " days ago ";
			}
		}
		else if (hours > 0) {
			if (hours == 1) {
				date = "about " + hours + " hour ago ";
			}
			else {
				date = "about " + hours + " hours ago ";
			}
		}
		else if (minutes > 0) {
			if (minutes == 1) {
				date = minutes + " minute ago ";
			}
			else {
				date = minutes + " minutes ago ";
			}
		}
		else {
			if (seconds == 1) {
				date = seconds + " second ago ";
			}
			else {
				date = seconds + " seconds ago ";
			}
		}
		
		*/
		
		
		String source = " via " + item.source().toString();
		
		String metaString = date + source;

		metadata = new JLabel(metaString);

		Text.setLineWrap(true);
		Text.setEditable(false);
		
		setLayout(new BorderLayout());
		
		add(Username, BorderLayout.NORTH);
		add(Picture, BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(metadata, BorderLayout.SOUTH);
	}
}
