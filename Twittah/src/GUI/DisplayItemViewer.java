package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

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
	
	public DisplayItemViewer(DisplayItem item)
	{
		setPreferredSize(new Dimension(400,100));
		
		Text.setText(item.text());
		Username = new JLabel(item.tweeter().getUserName());
		Picture = new JLabel(item.tweeter().getUserPicture());
		Source = new JLabel(item.source().toString());
		Date = new JLabel(item.date().toString());

		JPanel metadata = new JPanel();
		metadata.setLayout(new GridLayout(1,0));
		
		metadata.add(Source);
		metadata.add(Date);

		Text.setLineWrap(true);
		Text.setEditable(false);
		
		setLayout(new BorderLayout(2, 3));
		
		add(Username, BorderLayout.NORTH);
		add(Picture, BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(metadata, BorderLayout.SOUTH);
	}
}
