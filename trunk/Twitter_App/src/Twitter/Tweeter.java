package Twitter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.xpath.*;

import org.xml.sax.InputSource;

import RandomClasses.*;
import Timelines.UserTimeline;


/**
 * This is the user object.
 * 
 * @author Rick
 * @version 2/2/2009
 * 
 * @author Scott Smiesko
 * @version 2/24/2009
 * 
 */
public class Tweeter
{
	//Class Variables
	
	/**
	 * Stores the users info XML feed url
	 */
	private static String userXMLInfoURL = "http://api.twitter.com/1/users/show.xml?user_id=";
	/**
	 * Stores the users timeline XML feed url
	 */
	private String userXMLTimelineURL = null;
    /**
     * Stores the users unique id.
     */
    private String userID = null;
    /**
     * Stores the real name of the user
     */
    private String realName = null;
    /**
     * Stores the users screen name
     */
    private String screenName = null;
    /**
     * Stores the users location
     */
    private String userLocation = null;
    /**
     * Stores the users description
     */
    private String userDescription = null;
    /**
     * Stores the users home page
     */
    private String userHomePage = null;
    /**
     * Stores the users verification status
     */
    private String verifiedUser = null;
    /**
     * Stores the tweeter's profile picture.
     */
    private BufferedImage userPicture = null;
    /**
     * Stores the timeline for this user.
     */
    private UserTimeline timeline;
    
    private UserTimeline tweets;

    //Constructors
    
    /**
     * Constructor method that requires a UserID.  
     *  uses the userID to get the twitter Info XML File of that user
     * 
     * @author Scott Smiesko
     * @param  newUserID - userID used to get twitter feed
     */
    public Tweeter(String newUserID)
    {
    	userID = newUserID;
    	populateTweeterFromXML(userXMLInfoURL + userID);
    }

    // Methods
    
    /**
     * Populates users's tweets. Fairly fast process.
     */
    public void populateUserTimeline()
    {
    	timeline = new UserTimeline(this);
    }

    /**
     * Populates the rest of a tweeter object with data from the XML feed
     * 
     * @author Scott Smiesko
     * @param xmlFile
     */
    public void populateTweeterFromXML(String xmlFile)
    {
    	
    	XPath xpath = XPathFactory.newInstance().newXPath();
    	InputSource source = new InputSource (xmlFile);
    	
    	System.out.println("Grabbing XML from Twitter.. be patient");
    	
    	try {
			String result = xpath.evaluate("/user/protected", source);
			if (result == "false")
			{
				System.out.println("User feed is locked, bummer");
			}
			else
			{
				/**
				 * Set the attributes for our user
				 */
				realName = xpath.evaluate("/user/name", source);
				screenName = xpath.evaluate("/user/screen_name", source);
				userLocation = xpath.evaluate("/user/location", source);
				userDescription = xpath.evaluate("/user/description", source);
				userPicture = ImageIO.read(new URL(xpath.evaluate("/user/profile_image_url", source)));
				userHomePage = xpath.evaluate("/user/url", source);
				verifiedUser = xpath.evaluate("/user/verified", source);
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			userPicture = null;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This gets the person's unique id.
     *
     * @author Rick Humes
     * @return userID Person's unique id.
     */
    public String getUserID()
    {
        return userID;
    }
    
    /**
     * This gets the person's username
     *
     * @author Rick Humes
     * @return userName Person's username
     */
    public String getScreenName()
    {
        return screenName;
    }

    /**
     * This gets the person's name
     *
     * @author Rick Humes
     * @return name Person's name.
     */
	public String getRealName() 
	{
		return realName;
	}
    
    /**
     * This returns all attributes of a Tweeter
     * 
     * @author Scott Smiesko
     * @return {@code userInfo} - appended string of all Tweeter attributes
     */
    public String toString()
    {
    	String userInfo = null;
    	userInfo = "Info: "				+ userXMLInfoURL + 		"\n" +
    			   "Timeline: " 		+ userXMLTimelineURL + 	"\n" +
    			   "ID: " 				+ userID + 				"\n" +
    			   "Real Name: " 		+ realName + 			"\n" +
    			   "Screen Name: "		+ screenName + 			"\n" +
    			   "Location: "			+ userLocation + 		"\n" +
    			   "Description: "		+ userDescription + 	"\n" +
    			   "Home Page: "		+ userHomePage + 		"\n" +
    			   "Verified?: "		+ verifiedUser + 		"\n" +
    			   "Picture: "			+ userPicture + 		"\n" ;
        return userInfo;
    }
}