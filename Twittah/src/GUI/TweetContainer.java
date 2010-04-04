package GUI;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Twitter.Tweet;

/**
 * TweetContainer is a JPanel containing all the information in a tweet that
 * gets displayed in a composite timeline
 * would have.
 * @author Frappe051
 *
 */
public class TweetContainer extends JPanel
{

	/**
	 * 
	 */
	JLabel Username = null;
	/**
	 * 
	 */
	Image Picture = null;
	/**
	 * 
	 */
	JLabel Text = null;
	/**
	 * 
	 */
	JLabel Source = null;
	/**
	 * 
	 */
	JLabel Date = null;
	
	/**
	 * Constructor for creating a TweetContainer GUI object (in a JPanel) 
	 * 
	 * @param tweet
	 */
	public TweetContainer(Tweet tweet)
	{
		Text = new JLabel(tweet.getText());
		Username = new JLabel(tweet.getTweeter().getScreenName());
		Picture = tweet.getTweeter().getUserPicture();
		Source = new JLabel(tweet.getSource());
		Date = new JLabel(tweet.getDate().toString());
		

		JPanel metadata = new JPanel();
		
		metadata.add(Source);
		metadata.add(Date);

		
		setLayout(new BorderLayout());
		add(Username, BorderLayout.NORTH);
		//add(new JLabel(Picture), BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(metadata, BorderLayout.SOUTH);
		
		
		
	}
	
}
