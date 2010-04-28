package Timelines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import testing.ProgramState;
import testing.ProgramStateEvent;
import testing.ProgramStateListener;

import Changes.DisplayItem;
import Changes.DisplayItemOrganizer;
import Changes.OrganizeType;
import Changes.Timeline;
import Twitter.Tweet;

public class CompositeTimeline extends Timeline{
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	public void addTimeline(Timeline timeline)
	{
		System.out.println("Timeline Added");
		timelines.add(timeline);
		fill();
		timelineAdded();
	}
	
	private synchronized void timelineAdded()
	{
		ProgramStateEvent state = new ProgramStateEvent(this, ProgramState.TIMELINE_ADDED);
		ProgramStateListener[] listeners = new ProgramStateListener[_listeners.size()];
		_listeners.toArray(listeners);
        for(ProgramStateListener listener : listeners)
        {
        	System.out.println("Sending state" + state.state() + " to" + listener);
        	listener.stateReceived(state);
        }
	}
	
	public void clearAll()
	{
		clearItems();
		timelines.clear();
	}
	
	public void fill()
	{
		clearItems();
		for(Timeline timeline : timelines)
			for(DisplayItem displayItem : timeline.displayItems())
				addDisplayItem(displayItem);
		organize();
	}

	public void removeTimeline(Timeline timeline) {

		for(Timeline temp : timelines) {
			if (temp.equals(timeline)){
				timelines.remove(timeline);
			}
		}
		fill();
		
	}
	
	protected void reload() {}

	@Override
	public void downloadAndParse() {
		for(Timeline timeline :  timelines)
		{
			timeline.clearItems();
			timeline.downloadAndParse();
		}
		fill();
	}

	@Override
	public void saveTimeline() {
		for(Timeline timeline : timelines)
			timeline.saveTimeline();
	}

	
}
