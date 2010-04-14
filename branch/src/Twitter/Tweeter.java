package Twitter;

import java.awt.Component;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import Exceptions.TweeterException;


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
	private final String userXMLInfoURL = "http://api.twitter.com/1/users/show.xml";
	/**
	 * Stores the users timeline XML feed url
	 */
	private String userXMLTimelineURL = null;
    /**
     * Stores the users unique id.
     */
    private String user;
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
    private ImageIcon userPicture = null;
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
    public Tweeter(String newUser) throws TweeterException
    {
    	System.out.println("Creating Tweeter Object for: " + newUser );
    	
    	
    	//if(!validID(newUserID) && Integer.valueOf(newUserID) > 0) throw new InvalidUserID(newUserID);
    	user = newUser;
    	downloadXML();
    	//if(tweeterXML == null) throw new TweeterException(userID);
    	parseXML();
    }

    // Methods

    public boolean validID(String newUserID)
    {
    	boolean value = false;
    	
    	if(Integer.parseInt(newUserID) > -1)
    		value = true;
    	
    	return value;
    }
    
    private void downloadXML()
    {
		System.out.println("Downloading Tweeter Information.");
		
			try {
				tweeterXML = new SAXBuilder().build(new URL(userXMLInfoURL + "?user_id=" + user));
			} catch (Exception e) {
				e.printStackTrace();
				tweeterXML = null;
			}
			
			
			if(tweeterXML == null)
			{
				try {
					tweeterXML = new SAXBuilder().build(new URL(userXMLInfoURL + "?scree_name=" + user));
				} catch (Exception e) {
					e.printStackTrace();
					tweeterXML = null;
			}
			}
		
    }
    /**
     * Populates the rest of a tweeter object with data from the XML feed
     * 
     * @author Scott Smiesko
     * @param xmlFile
     */
    private void parseXML()
    {
       	System.out.println("Parsing Tweeter XML");
       	
       	Element element = tweeterXML.getRootElement();
       	
		realName = element.getChildText("name");
		screenName = element.getChildText("screen_name");
		userLocation = element.getChildText("location");
		userDescription = element.getChildText("description");
		userProtected = Boolean.parseBoolean(element.getChildText("protected"));
		try {
			userPicture = new ImageIcon(new URL(element.getChildText("profile_image_url")));
		} catch (MalformedURLException e) {
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
        return user;
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
	
	public ImageIcon getUserPicture()
	{
		return userPicture;
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
    			   "\tID: " 			+ 	user 				+ 	"\n\t" 	+
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