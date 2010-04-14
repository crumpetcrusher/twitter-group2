package Twitter;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.TweeterException;
import Timelines.UserTimeline;
import backend.XMLHelper;


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
     * Stores the users unique id.
     */
    private String userID;
    /**
     * Stores the real name of the user
     */
    private String realName = null;
    /**
     * Stores the users screen name
     */
    private String screenName = null;
    /**
     * Stores the tweeter's profile picture.
     */
    private ImageIcon userPicture = null;
    /**
     * Stores the timeline for this user.
     */
    private UserTimeline userTimeline = null;
    /**
     * Is the user protected?  Can we get their tweets?
     */
    private boolean userProtected = false;
    /**
     * document object for storing the XML
     */
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
    	userID = newUser;
    	getXML();
    	//if(tweeterXML == null) throw new TweeterException(userID);
    	parseXML();

    	userTimeline = new UserTimeline(this);
    	
    }

    // Methods

    public boolean validID(String newUserID)
    {
    	boolean value = false;
    	
    	if(Integer.parseInt(newUserID) > -1)
    		value = true;
    	
    	return value;
    }
    
    
    private void getXML()
    {
		System.out.println("Downloading Tweeter Information.");
		
		tweeterXML = XMLHelper.getUserInfoByUserID(userID);	
		
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
       	
       	Element user;
       	Element name;
       	Element screen_name;
       	Element isProtected;
       	Element picture;
       	
       	user = (Element)(tweeterXML.getFirstChild());
       	
       	
       	name = (Element)user.getElementsByTagName("name").item(0);
       	realName = name.getTextContent();
       	
        screen_name = (Element)(user.getElementsByTagName("screen_name").item(0));
        screenName = screen_name.getTextContent();
        
        isProtected = (Element)(user.getElementsByTagName("protected").item(0));
        userProtected = Boolean.parseBoolean(isProtected.getTextContent());
        
        picture = (Element)(user.getElementsByTagName("profile_image_url").item(0));
        try {
			userPicture = new ImageIcon(new URL(picture.getTextContent()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
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
	 */
	public String getRealName() 
	{
		return realName;
	}
	
	public ImageIcon getUserPicture()
	{
		return userPicture;
	}
	
	public UserTimeline getUserTimeline()
	{
		return userTimeline;
	}
	
	public boolean isProtected()

	{
		return userProtected;
	}


    
}