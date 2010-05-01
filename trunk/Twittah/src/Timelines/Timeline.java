//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project               : IST240 - Twitter Application
//
// Abstract Class Name   : Timeline
//    
// Authors               : Scott Smiesko, Rick Humes
// Date                  : 2010-30-04
//
//
// DESCRIPTION
// This is the backbone of all timelines used in the program which holds all the common information that a
// timeline would require.  It stores a list of DisplayItems (which is how usertimeline and searchTimeline are
// able to store their tweets as they inherit the abilities from this class) and has all the necessary means 
// of refreshing/deleting/adding/sorting displayitems for use in other timelines.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Timelines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;

import Changes.DisplayItem;
import Changes.DisplayItemOrganizer;
import Changes.OrganizeType;
import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;

public abstract class Timeline {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Abstract Class Attributes
    //
    
    // This class has 4 attributes used by a timeline:
    // 
    // displayItems             :  Stores the tweet messages that would go into a timeline
    //
    // _listeners               :  Stores all the current listeners needed for threading to work alongside all
    //                             other classes
    //
    // currentOrganize          :  Stores the way the timeline should organize itself.  Can be 
    //                             ascending/descending by alphabetical or by date.
    //
    // timelineXML              :  A storage place for the XML feed downloaded from twitter.
    //
    //
    protected List<DisplayItem> displayItems    = new ArrayList<DisplayItem>();
    protected List              _listeners      = new ArrayList();
    private   OrganizeType      currentOrganize = OrganizeType.JAN_DEC;
    protected Document          timelineXML     = null;

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Abstract Class Methods
    //
    
    // This method returns all of the display items stored in a timeline
    //
    public DisplayItem[] displayItems() {
        return displayItems.toArray(new DisplayItem[displayItems.size()]);
    }

    // This method adds a displayItem to the list of displayItems
    //
    protected void addDisplayItem(DisplayItem newDisplayItem) {
        displayItems.add(newDisplayItem);
        timelineModified();
    }

    // This will clear all the items in displayItems
    //
    public void clearItems() {
        displayItems.clear();
    }

    // Displays the list of displayItems as a string for console output
    //
    public String toString() {
        String value = "[Timeline]" + "\r\n";
        for (DisplayItem displayItem : displayItems)
            value += "\t" + displayItem.toString() + "\r\n";
        return value;
    }

    // Returns the current way we are organizing the timeline.
    //
    public OrganizeType getOrganizeType() {
        return currentOrganize;
    }

    // Called to organize the timeline if new displayItems are added.
    //
    public void organize() {
        Collections.sort(displayItems, new DisplayItemOrganizer(currentOrganize));
        timelineModified();
    }

    // Sets the way we are to be organizing the timeline
    //
    public void setOrganizeType(OrganizeType type) {
        currentOrganize = type;
    }

    // Abstract method to downloadAndParse the new information from an XML feed
    //
    public abstract void downloadAndParse();

    // Abstract method to save the timeline to disk
    //
    public abstract void saveTimeline();
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Abstract Class Threading
    //

    // Called when a timeline is added, which will inform all listeners of this action has happened.
    //
    @SuppressWarnings("unchecked")
    protected synchronized void timelineModified() {
        ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.TIMELINE_MODIFIED);
        ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
        _listeners.toArray(listeners);
        for (ProgramStateListener listener : listeners)
            listener.stateReceived(state);
    }
    
    // Used to add a programStateListener so that listener will be informed when timelineModified()
    // above has happened.
    //
    @SuppressWarnings("unchecked")
    public synchronized void addProgramStateListener(ProgramStateListener l) {
        if (_listeners.add(l))
            System.out.println("Listener Added");
        else
            System.out.println("Listener Not Added");

    }

    // Removes a programStateListener when it's no longer required to be listening to this class
    //
    public synchronized void removeProgramStateListener(ProgramStateListener l) {
        _listeners.remove(l);
    }

}
