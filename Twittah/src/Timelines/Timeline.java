package Timelines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backend.AutomaticTimelineRefresher;

import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;


import Changes.DisplayItem;
import Changes.DisplayItemOrganizer;
import Changes.OrganizeType;

public abstract class Timeline {
	
	protected List<DisplayItem> displayItems = new ArrayList<DisplayItem>();
	
	protected List _listeners = new ArrayList();
	
	private OrganizeType currentOrganize  = OrganizeType.JAN_DEC;
	
	protected Document timelineXML = null;
	
	public DisplayItem[] displayItems()
	{
		return displayItems.toArray(new DisplayItem[displayItems.size()]);
	}
	
	protected void addDisplayItem(DisplayItem newDisplayItem)
	{
		displayItems.add(newDisplayItem);
		timelineModified();
	}
	
	public void clearItems()
	{
		displayItems.clear();
	}
	
	public String toString()
	{
		String value = "[Timeline]" + "\r\n";
		for(DisplayItem displayItem : displayItems)
			value += "\t" + displayItem.toString() + "\r\n";
		return value;
	}
	
	public OrganizeType getOrganizeType()
	{
		return currentOrganize;
	}
	
	public void organize()
	{
		Collections.sort(displayItems, new DisplayItemOrganizer(currentOrganize));
		timelineModified();
	}

	public void setOrganizeType(OrganizeType type) {
		currentOrganize = type;
	}
        
        // Called when a timeline is added, which will inform all listeners of this action has happened.
        //
        @SuppressWarnings("unchecked")
        protected synchronized void timelineModified()
        {
            ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.TIMELINE_MODIFIED);
            ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
            _listeners.toArray(listeners);
            for(ProgramStateListener listener : listeners)
                listener.stateReceived(state);
        }


	public abstract void downloadAndParse();
	
	public abstract void saveTimeline();
	
	   @SuppressWarnings("unchecked")
	public synchronized void addProgramStateListener( ProgramStateListener l ) {
	        if(_listeners.add( l ))
	 		   System.out.println("Listener Added");
	        else
	 		   System.out.println("Listener Not Added");
	        	
	    }
	    
	    public synchronized void removeProgramStateListener( ProgramStateListener l ) {
	        _listeners.remove( l );
	    }
	
}
	


	
