package Twitter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import testing.ProgramState;
import testing.ProgramStateEvent;
import testing.ProgramStateListener;

import Changes.SubscriptionItem;
import Changes.Timeline;
import Exceptions.TweeterException;
import Timelines.SearchTimeline;
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
public class Tweeter implements SubscriptionItem
{
	
	//Class Variables

    /**
     * Stores the real name of the user
     */
    private String userName = null;
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
    
    private Thread thread;
    
    private List _listeners = new ArrayList();

    //Constructors
    
    /**
     * Constructor method that requires a UserID.  
     *  uses the userID to get the twitter Info XML File of that user
     * 
     * @author Scott Smiesko
     * @param  newUserID - userID used to get twitter feed
     */
    public Tweeter(String name) throws TweeterException
    {
    	System.out.println("Creating Tweeter Object for: " + name );
    	
    	userName = name;
        thread = (new Thread() {
            public void run() {
                    do {
                            getXML();
							if(tweeterXML != null)
							parseXML();
							suspend();
                            
                    } while (isAlive());
            }
    });
        	thread.start();


    	userTimeline = new UserTimeline(this);
    	
    }
    
    public Tweeter(String name, ImageIcon image, Timeline timeline)
    {
    	userName = name;
    	userPicture = image;
    	userTimeline = (UserTimeline)timeline;
    	
    	if(userPicture == null)
			try {
				userPicture = new ImageIcon(new URL("http://s.twimg.com/a/1271891196/images/default_profile_5_normal.png"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tweeterParsed();
    }

    // Methods

    public boolean validName(String newUserName)
    {
    	boolean value = false;
    	
    	if(newUserName != null)
    		value = true;
    	
    	return value;
    }
    
    
    private void getXML()
    {
		System.out.println("Downloading Tweeter Information.");
		
		tweeterXML = XMLHelper.getUserInfoByUserSN(userName);	
		
    }
    /**
     * Populates the rest of a tweeter object with data from the XML feed
     * 
     * @author Scott Smiesko
     * @param xmlFile
     */
    private void parseXML()
    {
       	System.out.println("Parsing " + userName + " XML");
       	
       	Element user;
       	Element isProtected;
       	Element picture;
       	
       	user = (Element)(tweeterXML.getFirstChild());
        
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
		tweeterParsed();
       	
    }
    
/* 
    **
     * This gets the person's unique id.
     *
     * @author Rick Humes
     * @return userID Person's unique id.
     *
    public String getUserID()
    {
        return userID;
    }
*/
    
    /**
     * This gets the person's username
     *
     * @author Rick Humes
     * @return userName Person's username
     */
    public String getUserName()
    {
        return userName;
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

	@Override
	public ImageIcon icon() {
		return userPicture;
	}

	@Override
	public String text() {
		return userName;
	}

	@Override
	public boolean isSearch() {
		return false;
	}

	@Override
	public Timeline timeline() {
		return userTimeline;
	}
	
	   @SuppressWarnings("unchecked")
	public synchronized void addProgramStateListener( ProgramStateListener l ) 
   {
        if(_listeners.add( l ))
 		   System.out.println("Listener Added");
        else
 		   System.out.println("Listener Not Added");
        	
    }
    
    public synchronized void removeProgramStateListener( ProgramStateListener l ) {
        _listeners.remove( l );
    }
    
	private synchronized void tweeterParsed()
	{
		System.out.println(userName + " parsed! " + _listeners.size() + " objects listening!");
		ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.SUBSCRIPTION_ADDED);
		ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
		_listeners.toArray(listeners);
        for(ProgramStateListener listener : listeners)
        {
        	System.out.println("Sending state" + state.state() + " to" + listener);
        	listener.stateReceived(state);
        }
	}


    
}