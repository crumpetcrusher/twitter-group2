package GUI;
import java.awt.*;
import javax.swing.*;

/**
 * TweetContainer is a JPanel containing all the information in a tweet that
 * gets displayed in a composite timeline
 * would have.
 * @author Frappe051
 *
 */
public class TweetContainer extends JPanel
{

	JLabel Username = null;
	JLabel Picture = null;
	JLabel Text = null;
	JLabel Source = null;
	JLabel Date = null;
	
	/**
	 * Constructor for creating a TweetContainer GUI object (in a JPanel) 
	 * 
	 * @param newUserName
	 * @param newUserPic
	 * @param newText
	 * @param newSource
	 * @param newDate
	 */
	public TweetContainer(String newUserName, String newUserPic, String newText, String newSource, String newDate)
	{
		Text = new JLabel(newText);
		Username = new JLabel(newUserName);
		Picture = new JLabel(new ImageIcon(newUserPic));
		Source = new JLabel(newSource);
		Date = new JLabel(newDate);
		
		/*
		JPanel metadata = new JPanel();
		metadata.setLayout(new BorderLayout());
		
		metadata.add(Source, BorderLayout.WEST);
		metadata.add(Date, BorderLayout.EAST);
		*/
		
		setLayout(new BorderLayout());
		add(Username, BorderLayout.NORTH);
		add(Picture, BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(new JLabel("Source + Date!"), BorderLayout.SOUTH);
		
		
		
	}
	/**
	 * Constructor for creating a test tweet container
	 */
	public TweetContainer(){
		Text = new JLabel("This is a test text object");
		Username = new JLabel("Maybe i'm scott?");
		Picture = new JLabel("Hurr, pic goes here");
		Source = new JLabel("From TEH WEB");
		Date = new JLabel("on 4/5/2010");
		
		/*
		JPanel metadata = new JPanel();
		metadata.setLayout(new BorderLayout());
		
		metadata.add(Source, BorderLayout.WEST);
		metadata.add(Date, BorderLayout.EAST);
		*/
		
		setLayout(new BorderLayout());
		add(Username, BorderLayout.NORTH);
		add(Picture, BorderLayout.WEST);
		add(Text, BorderLayout.CENTER);
		add(new JLabel("Source + Date!"), BorderLayout.SOUTH);
	}
	
}
