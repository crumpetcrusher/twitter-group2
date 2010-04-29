package Twitter;

//Import Statements
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import testing.*;
import Changes.SubscriptionItem;
import Changes.Timeline;
import Exceptions.TweeterException;
import Timelines.UserTimeline;
import backend.XMLHelper;


//The tweeter class!
public class Tweeter implements SubscriptionItem
{
	
//Class Variables

    //Stores the real name of the user
    private String _userName = null;
    
    //Stores the tweeter's profile picture.
    private ImageIcon _userPicture = null;
    
    //Stores the timeline for this user.
    private UserTimeline _userTimeline = null;
    
    //Document object for storing the XML
    private Document _tweeterXML = null;
    
    //This thread is used to download and parse the tweeter data, without interrupting the program
    private Thread thread = (new Thread() {
        @SuppressWarnings("deprecation")
        public void run() {
            getXML();
            if(_tweeterXML != null)
                parseXML();
            suspend();
        }
    });
    
    //This is for the ProgramStateListener, it holds every object that is listening for the tweeter parsing complete event.
    @SuppressWarnings("unchecked")
    private List _listeners = new ArrayList();

    
//Class Constructors
    
    //Constructor method that requires a user name.  
    public Tweeter(String userName) throws TweeterException
    {
        //Initializing all the variables
    	_userName = userName;
    	
        //Creates the user's timeline
        _userTimeline = new UserTimeline(this);
    
    	//Starts the thread which downloads and parses the information
    	thread.start();
    }
    
    //This constructor allows for the creation of a user without having to download the user's information.
    public Tweeter(String userName, ImageIcon userPicture, Timeline userTimeline)
    {
        //Assigning the inputed variables
    	_userName = userName;
    	_userPicture = userPicture;
    	_userTimeline = (UserTimeline)userTimeline;
    	
    	
    	//Assign a default picture if none is specified
    	if(_userPicture == null)
    	{
    	    try 
    	    {
    	        _userPicture = new ImageIcon(new URL("http://s.twimg.com/a/1271891196/images/default_profile_5_normal.png"));
	    } 
	    catch (MalformedURLException e) 
	    {
	        e.printStackTrace();
	    }
    	}
    	
    	//Let the listeners know that the Tweeter object is completed.
    	tweeterParsed();
    }

//Class Methods

    //Get the XML associated to the userName provided
    private void getXML()
    {
        _tweeterXML = XMLHelper.getUserInfoByUserSN(_userName);		
    }
    
    //Populates the rest of a tweeter object with data from the XML feed
    private void parseXML()
    {
       	Element user = (Element)(_tweeterXML.getFirstChild());
       	Element picture = (Element)(user.getElementsByTagName("profile_image_url").item(0));
       	
       	//Try to download and set the user's picture
        try 
        {
            _userPicture = new ImageIcon(new URL(picture.getTextContent()));
        } 
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        }
        
        //Let the listeners know that the Tweeter object is completed.
        tweeterParsed();
       	
    }
    
    //Returns the user's picture
    @Override
    public ImageIcon icon() 
    {
        return _userPicture;
    }

    //Returns the user's name
    @Override
    public String text() {
        return _userName;
    }

    //Returns that this DisplayItem is not a search
    @Override
    public boolean isSearch() {
        return false;
    }

    //Return the user's timeline
    @Override
    public Timeline timeline() {
        return _userTimeline;
    }

    
    //This is for threading
    
    //Adds a listener
    @SuppressWarnings("unchecked")
    public synchronized void addProgramStateListener( ProgramStateListener l ) 
    {
        _listeners.add(l);
    }
    
    //Removes a listener
    public synchronized void removeProgramStateListener( ProgramStateListener l ) 
    {
        _listeners.remove(l);
    }
    
    //Informs listeners that the tweeter object is completed.
    @SuppressWarnings("unchecked")
    private synchronized void tweeterParsed()
    {
        ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.SUBSCRIPTION_ADDED);
        ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
        _listeners.toArray(listeners);
        
        for(ProgramStateListener listener : listeners)
        	listener.stateReceived(state);
    }
    
}