//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : CompositeTimeline
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This is a extention of Timeline which will store multiple timelines. The state listener is for the listening of
// whether or not a timeline is done downloading and parsing.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Timelines;

import java.util.ArrayList;
import java.util.List;
import Changes.DisplayItem;
import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;

public class CompositeTimeline extends Timeline  implements ProgramStateListener{
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class has 1 component used to store timelines:
    //
    // timelines        : Stores the timelines.
    //
    //
    private List<Timeline> timelines = new ArrayList<Timeline>();
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // This method allows a timeline to be passed in and added to our list.
    //
    public void addTimeline(Timeline timeline)
    {
        // Won't add the timeline if it exists
        //
        if(!timelines.contains(timeline))
        {
            timelines.add(timeline);
            timeline.addProgramStateListener(this);
            fill();
        }
    }

    // Clears all display items and timelines stored.
    //
    public void clearAll()
    {
        clearItems();
        timelines.clear();
        timelineModified();
    }
    
    // Clears the display items and fills in according to what's in the timelines
    //
    public void fill()
    {
        clearItems();
        for(Timeline timeline : timelines)
                for(DisplayItem displayItem : timeline.displayItems())
                        addDisplayItem(displayItem);
        organize();
    }

    // Removes a timeline from our list
    //
    public void removeTimeline(Timeline timeline) 
    {
        for(Timeline temp : timelines) 
            if (temp.equals(timeline))
                timelines.remove(timeline);
        fill();
    }

    // An override, meant to download and parse the timeline
    // As this timeline has multiple, it'll refresh them.
    // 
    @Override
    public void downloadAndParse() 
    {
        for(Timeline timeline :  timelines)
        {
            timeline.clearItems();
            timeline.downloadAndParse();
        }
    }

    // Saves the timelines stored, to their respective files.
    //
    @Override
    public void saveTimeline() {
            for(Timeline timeline : timelines)
                    timeline.saveTimeline();
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Threading
    //
    
    protected void reload() {}
    
    
    // This method is called be the listenee, aka timeline, when it is done downloading and parsing.
    // Which when this is called, it will fill the displayitems
    //
    public void stateReceived(ProgramStateEvent event) 
    {
        if(event.state() == ProgramState.TIMELINE_MODIFIED || event.state() == ProgramState.TIMELINE_REFRESHED)
            fill();
    }
	
}
