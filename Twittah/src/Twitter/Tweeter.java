//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : Tweeter
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class stores and parses information about a tweeter.  It can either be constructed by using a name, or 
// by passing all of the required information to it directly (i.e. when the program loads up and displays a users
// timeline already saved to disk).  Thread-safe and implements programStateListeners/Senders to know when things
// have been created or destroyed.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Twitter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Changes.SubscriptionItem;
import ThreadingHelpers.*;
import Timelines.Timeline;
import Timelines.UserTimeline;
import backend.XMLHelper;

@SuppressWarnings("unchecked")
public class Tweeter implements SubscriptionItem {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class has 6 attributes used manage information about a tweeter.
    // 
    // _userName                :  A String to store the name of the tweeter.
    //
    // _userPicture             :  An ImageIcon to store the profile picture of the tweeter.
    //
    // _userTimeline            :  The timeline for that specific user.
    //
    // _tweeterXML              :  The XML document that has the tweeters information.
    //
    // _listeners               :  For ProgramStateListener.  It holds every object that is listening for the
    //                             the "tweeter parsing complete" event.
    //
    // thread                   :  A thread used to download and parse the tweeter data without interrupting 
    //                             the program.
    //
    //
    private String       _userName     = null;
    private ImageIcon    _userPicture  = null;
    private UserTimeline _userTimeline = null;
    private Document     _tweeterXML   = null;
    private List         _listeners    = new ArrayList();
    private Thread thread = (new Thread() {
        @SuppressWarnings("deprecation")
        public void run() {
            getXML();
            if (_tweeterXML != null)
                parseXML();
            suspend();
            }
        });

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // A constructor method that only requires a username to download and parse a tweeter
    //
    public Tweeter(String userName) {
        
        // Initializing all the variables
        //
        _userName = userName;

        // Creates the user's timeline
        //
        _userTimeline = new UserTimeline(this);

        // Starts the thread which downloads and parses the information
        //
        thread.start();
    }

    // This constructor allows for the creation of a user without having to download the user's information.
    //
    public Tweeter(String userName, ImageIcon userPicture, Timeline userTimeline) {
        
        // Assigning the passed in variables
        //
        _userName = userName;
        _userPicture = userPicture;
        _userTimeline = (UserTimeline) userTimeline;

        // Assign a default picture if none is specified
        //
        if (_userPicture == null) {
            try {
                _userPicture = new ImageIcon(
                        new URL("http://s.twimg.com/a/1271891196/images/default_profile_5_normal.png"));
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        // Let the listeners know that the Tweeter object is completed.
        //
        tweeterParsed();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //

    // Get the XML associated to the userName provided
    //
    private void getXML() {
        _tweeterXML = XMLHelper.getUserInfoByUserSN(_userName);
    }

    // This method populates the rest of a tweeter object with data from the XML feed
    //
    private void parseXML() {
        Element user = (Element) (_tweeterXML.getFirstChild());
        Element picture = (Element) (user
                .getElementsByTagName("profile_image_url").item(0));

        // Try to download and set the user's picture
        //
        try {
            _userPicture = new ImageIcon(new URL(picture.getTextContent()));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Let the listeners know that the Tweeter object is completed.
        //
        tweeterParsed();

    }

    // Returns the user's picture
    //
    @Override
    public ImageIcon icon() {
        return _userPicture;
    }

    // Returns the user's name
    //
    @Override
    public String text() {
        return _userName;
    }

    // Returns that this DisplayItem is not a search
    //
    @Override
    public boolean isSearch() {
        return false;
    }

    // Return the user's timeline
    //
    @Override
    public Timeline timeline() {
        return _userTimeline;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Threading Methods
    //

    // Adds a listener
    //
    @SuppressWarnings("unchecked")
    public synchronized void addProgramStateListener(ProgramStateListener l) {
        _listeners.add(l);
    }

    // Removes a listener
    //
    public synchronized void removeProgramStateListener(ProgramStateListener l) {
        _listeners.remove(l);
    }

    // Informs listeners that the tweeter object is completed.
    //
    @SuppressWarnings("unchecked")
    private synchronized void tweeterParsed() {
        ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.SUBSCRIPTION_ADDED);
        ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
        _listeners.toArray(listeners);

        for (ProgramStateListener listener : listeners)
            listener.stateReceived(state);
        
    }
}
