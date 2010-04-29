package backend;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import Changes.OrganizeType;
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

	public TimelinesManager(SubscriptionsManager newSubscriptionsMgr, RootGUI gui) {
		compositeTimeline.addProgramStateListener(this);
		timelinesVwr = new TimelinesViewer(this);
		subscriptionsMgr = newSubscriptionsMgr;
		loadPreviousTimelines();
		gui.add(timelinesVwr);
	}
	
	protected void initialize() {
		
		for (SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())
			compositeTimeline.addTimeline(subscriptItem.timeline());
		timelinesVwr.refresh();
	}

	public Timeline getCompositeTimeline() {
		return compositeTimeline;
	}
	
	public void organize(OrganizeType type)
	{
		compositeTimeline.setOrganizeType(type);
		compositeTimeline.organize();
		timelinesVwr.refresh();
		
	}
	public void setOrganizeType(OrganizeType type) {
		compositeTimeline.setOrganizeType(type);
	}
	
	public void clearTimeline() {
		compositeTimeline.clearAll();
		timelinesVwr.refresh();
	}
	
	public OrganizeType organizeType()
	{
		return compositeTimeline.getOrganizeType();
	}
	
	public void addToTimeline(SubscriptionItem subscriptItemToAdd) {
		for(SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())
			if (subscriptItem.equals(subscriptItemToAdd))
				compositeTimeline.addTimeline(subscriptItem.timeline());
	}
	
	public void removeFromTimeline(SubscriptionItem item)
	{
	    compositeTimeline.removeTimeline(item.timeline());
	}

	public void removeUserFromTimeline(String name) {

		for(SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())
			if (subscriptItem.text().equals(name))
				compositeTimeline.removeTimeline(subscriptItem.timeline());
		timelinesVwr.refresh();
	}

	
	public void refreshTimeline() {
		compositeTimeline.downloadAndParse();
		timelinesVwr.refresh();
	}
	
	
	public void addSearchToTimeline(String query) {
		SearchTimeline searchTimeline = new SearchTimeline(query);
		compositeTimeline.addTimeline(searchTimeline);
		timelinesVwr.refresh();
	}
	
	public void addTimeline(Timeline timeline)
	{
		compositeTimeline.addTimeline(timeline);
		//timelinesVwr.refresh();
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
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
				System.out.println("Loading " + file);
				doc = XMLHelper.getDocumentByLocation("src/" + file);
				if(file.contains("user"))
					addTimeline(UserTimeline.parseFromDocument(doc));
				else
					addTimeline(SearchTimeline.parseFromDocument(doc, "test"));
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
	    System.out.println("State Received: " + event.state() );
	    if(event.state() == ProgramState.TIMELINE_ADDED || event.state() == ProgramState.TIMELINE_REFRESHED)
	    {
	        System.out.println("Refresh TimelineViewer!");
	        timelinesVwr.refresh();
	    }
	}
}
