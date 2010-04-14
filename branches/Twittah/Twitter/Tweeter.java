package Twitter;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import Exceptions.TweeterException;
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
    	downloadXML();
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
    
    
    private void downloadXML()
    {
		System.out.println("Downloading Tweeter Information.");

		
			try {
				System.out.println("from user_id");
				tweeterXML = new SAXBuilder().build(new URL(userXMLInfoURL + "?user_id=" + userID));
			} catch (Exception e) {
				e.printStackTrace();
				tweeterXML = null;
			}
			
			
			if(tweeterXML == null)
			{
				try {
					System.out.println("from screen_name");
					tweeterXML = new SAXBuilder().build(new URL(userXMLInfoURL + "?screen_name=" + userID));
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