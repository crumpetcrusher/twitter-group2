package Twitter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.xpath.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
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
    private boolean userProtected = false;
    
    private Document tweeterXML = null;

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
    	System.out.println("Creating Tweeter Object for User ID: " + newUserID );
    	userID = newUserID;
    	downloadXML();
    	parseXML();
    }

    // Methods

    
    public void downloadXML()
    {
		System.out.println("Downloading Tweeter Information.");

		try 
		{
			tweeterXML = new SAXBuilder().build(new URL(userXMLInfoURL + userID));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			tweeterXML = null;
		}
    }
    /**
     * Populates the rest of a tweeter object with data from the XML feed
     * 
     * @author Scott Smiesko
     * @param xmlFile
     */
    public void parseXML()
    {
       	System.out.println("Parsing Tweeter XML");
       	
       	Element element = tweeterXML.getRootElement();
       	
		realName = element.getChildText("name");
		screenName = element.getChildText("screen_name");
		userLocation = element.getChildText("location");
		userDescription = element.getChildText("description");
		userProtected = Boolean.parseBoolean(element.getChildText("protected"));
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
	
	public boolean isProtected()
	{
		return userProtected;
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
    	userInfo = "[Tweeter Object]"							+	"\n\t"	+
    			   "\tInfo: "			+ 	userXMLInfoURL 		+ 	"\n\t" 	+
    			   "\tTimeline: " 		+ 	userXMLTimelineURL 	+ 	"\n\t" 	+
    			   "\tID: " 			+ 	userID 				+ 	"\n\t" 	+
    			   "\tReal Name: " 		+ 	realName 			+ 	"\n\t"	+
    			   "\tScreen Name: "	+ 	screenName 			+ 	"\n\t" 	+
    			   "\tLocation: "		+ 	userLocation 		+ 	"\n\t" 	+
    			   "\tDescription: "	+ 	userDescription 	+ 	"\n\t" 	+
    			   "\tHome Page: "		+ 	userHomePage 		+	"\n\t" 	+
    			   "\tVerified?: "		+ 	verifiedUser 		+ 	"\n\t" 	+
    			   "\tPicture: "		+ 	userPicture 		+ 	"\n\t" 	;
        return userInfo;
    }
}