package Twitter;
import java.net.URL;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Timeline timeline = new Timeline(Timeline_Type._friends);
		//timeline.set_Count(200);
		//timeline.set_Page(17);
		test_Tweet(Tweeter.Test_Tweeter_1);
		//test_User();
	}
	
	
	
	private static Tweet test_Tweet(Tweeter userIn)
	{
		try
		{			
			//Change these variables to test Tweet.
			Tweeter 	user 	= 	userIn;
			int 	id 		= 	123456;
			String 	text 	= 	"Join me for tea, please.";
			Date 	date 	= 	new Date(2010,0,16,1,41);
			Source 	source	= 	Source.Tweet_Deck;
			
			System.out.println("--Tweet Class Test Configuration--");
			System.out.println("User: \t\t" + user);
			System.out.println("ID: \t\t" + id);
			System.out.println("Text: \t\t" + text);
			System.out.println("Date: \t\t" + date);
			System.out.println("Source: \t" + source);
			
			Tweet tweet = new Tweet();
			System.out.println("--Testing Tweet Class Sets--");
			System.out.println("Person Set: \t" + tweet.setPerson(user));
			System.out.println("ID Set: \t" + tweet.setID(id));
			System.out.println("Text Set: \t" + tweet.setText(text));
			System.out.println("Date Set: \t" + tweet.setDate(date));
			//System.out.println("Source Set: \t" + tweet.setSource(source));	
			
			System.out.println("--Testing Tweet Class Gets--");
			System.out.println("Person: \t" + tweet.getPerson());
			System.out.println("Tweet ID: \t" + tweet.getID());
			System.out.println("Text: \t\t" + tweet.getText());
			System.out.println("Date: \t\t" + tweet.getDate());
			//System.out.println("Source: \t" + tweet.getSource());
			System.out.println("");
			
			return tweet;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private static Tweeter test_User()
	{
		try
		{
			//Change these variables to test User.
			int id = 54321;
			String 			userName 	=	"The Mad Hatter";
			String 			name 		=	"Hatta";
			BufferedImage 	image 		=	ImageIO.read(new URL("http://i.zdnet.com/blogs/twitter_logo.png"));
			Tweet[] 		tweets 		=	{Tweet.Test_Tweet_1, Tweet.Test_Tweet_2, Tweet.Test_Tweet_3};
			//Source			by_source	=	Source.IPhone;
			int				find_id		=	1;
			String			search		=	"tea";
			boolean			case_sensitive_search = false;
			
			System.out.println("--User Class Test Configuration--");
			System.out.println("ID: \t\t" + id);
			System.out.println("Username: \t" + userName);
			System.out.println("Name: \t\t" + name);
			System.out.println("Picture: \t" + image);
			//System.out.println("by_source: \t" + by_source);
			System.out.println("find_id: \t" + find_id);
			System.out.println("search: \t" + search);
			System.out.println("case_sensitive_search: \t" + case_sensitive_search);
			
			Tweeter person = new Tweeter();
			System.out.println("--Testing User Class Sets--");
			System.out.println("ID: \t\t" + person.setUserID(id));
			System.out.println("Username: \t" + person.setUserName(userName));
			System.out.println("Name: \t\t" + person.setName(name));
			System.out.println("Picture: \t" + person.setPicture(image));
			
			System.out.println("--Testing User Class Adds--");
			
			for(Tweet tweet : tweets)
			{
				System.out.println("Add to Tweets (" + tweet + "): " + person.addTweet(tweet));
			}
			
			System.out.println("--Testing User Class Gets--");
			System.out.println("ID: \t\t" + person.getUserID());
			System.out.println("Username: \t" + person.getUserName());
			System.out.println("Name: \t\t" + person.getName());
			System.out.println("Picture: \t" + person.getPicture());
			//System.out.println("Tweets by source (" + by_source + "): " + person.getTweets(by_source).length);
			System.out.println("Tweet by id (1): " + person.getTweet(find_id));
			System.out.println("Tweets by search (" + search + "): " + person.getTweets(search, case_sensitive_search).length);
			System.out.println("");
			
			return person;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
