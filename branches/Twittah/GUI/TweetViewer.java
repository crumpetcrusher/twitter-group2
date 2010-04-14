package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Twitter.Tweet;

/**
 * TweetContainer is a JPanel containing all the information in a tweet that
 * gets displayed in a composite timeline
 * would have.
 * @author Frappe051
 *
 */
public class TweetViewer extends JPanel
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
	public TweetViewer(Tweet tweet)
	{
		
		setPreferredSize(new Dimension(400,100));
		
		Text.setText(tweet.getText());
		Username = new JLabel(tweet.getTweeter().getScreenName());
		Picture = new JLabel(tweet.getTweeter().getUserPicture());
		Source = new JLabel(tweet.getMethod());
		Date = new JLabel(tweet.getDate().toString());
		

		JPanel metadata = new JPanel();
		
		metadata.add(Source);
		metadata.add(Date);

		Text.setLineWrap(true);
		Text.setEditable(false);
		
		setLayout(new BorderLayout());
		add(Username, BorderLayout.NORTH);
		add(Picture, BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(metadata, BorderLayout.SOUTH);
		
		
		
	}
	
}
