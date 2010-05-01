package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import Changes.OrganizeType;
import Changes.Search;
import Changes.SubscriptionItem;
import GUI.RootGUI;
import GUI.TimelinesViewer;
import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;
import Timelines.CompositeTimeline;
import Timelines.SearchTimeline;
import Timelines.Timeline;
import Timelines.UserTimeline;
import Twitter.Tweeter;

public class TimelinesManager implements ProgramStateListener {
	
	private SubscriptionsManager subscriptionsMgr;
	private TimelinesViewer		 timelinesVwr;
	private CompositeTimeline compositeTimeline = new CompositeTimeline();
	private AutomaticTimelineRefresher refresher = new AutomaticTimelineRefresher(compositeTimeline, 30);

	public TimelinesManager(SubscriptionsManager newSubscriptionsMgr, RootGUI gui) {
		compositeTimeline.addProgramStateListener(this);
		timelinesVwr = new TimelinesViewer(this);
		subscriptionsMgr = newSubscriptionsMgr;
		loadPreviousTimelines();
		gui.add(timelinesVwr);
	}
	
	protected void initialize() {
		for (SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())     
	            addToTimeline(subscriptItem);
	}
	
	public void toggleAutomaticRefresh()
	{
            if(refresher.isPaused())
                refresher.Resume();
            else
                refresher.Suspend();
	}

	public Timeline getCompositeTimeline() {
		return compositeTimeline;
	}
	
	public void organize(OrganizeType type)
	{
		compositeTimeline.setOrganizeType(type);
		compositeTimeline.organize();
	}
	
	public void clearTimeline() {
		compositeTimeline.clearAll();
	}
	
	public OrganizeType organizeType()
	{
		return compositeTimeline.getOrganizeType();
	}
	
	public void addToTimeline(SubscriptionItem subscriptItemToAdd) {
	    Timeline subscriptTimeline = subscriptItemToAdd.timeline();
            compositeTimeline.addTimeline(subscriptTimeline);
	}
	
	public void refreshTimeline() 
	{
            compositeTimeline.downloadAndParse();
	}
	
	public void addTimeline(Timeline timeline)
	{
	    compositeTimeline.addTimeline(timeline);
	}
	
	public void saveTimelines()
	{
	    compositeTimeline.saveTimeline();
	}
	
	public void loadPreviousTimelines()
	{
		System.out.println("Loading Previous Timelines");
		File dir = new File("src");
		String[] files = dir.list();
		Document doc = null;
	        Matcher matcher;
	        Timeline timeline = null;
	        SubscriptionItem sub = null;
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
		                matcher = Pattern.compile("(?<=\\_).+?(?=\\.xml)").matcher(file);
		                if(matcher.find())
		                {
        		                System.out.println("Loading " + matcher.group(0));
        				doc = XMLHelper.getDocumentByLocation("src/" + file);
        				if(file.contains("user"))
        				    //timeline = UserTimeline.parseFromDocument(matcher.group(0), doc);
        				    sub = Tweeter.getTweeterFromDoc(doc);
        				else
        				    timeline = SearchTimeline.parseFromDocument(matcher.group(0), doc);
        				
        				if(timeline != null)
        				    addTimeline(timeline);
        				if(sub  != null)
        				    addToTimeline(sub);
		                }
			}
	}
	
	public void deletePreviousTimelines()
	{
		File dir = new File("src");
		String[] files = dir.list();
		Document doc = null;
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
				File temp = new File("src/" + file);
				if(temp.delete())
					System.out.println("File Deleted: " + file);
				else
					System.out.println(file + " not delteed!");
			}
	}

	@Override
	public void stateReceived(ProgramStateEvent event) {
	    if(event.state() == ProgramState.TIMELINE_MODIFIED || event.state() == ProgramState.TIMELINE_REFRESHED)
	        timelinesVwr.refresh();
	}
}
