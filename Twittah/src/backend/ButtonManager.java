package backend;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.SwingUtilities;

import org.w3c.dom.Document;

import Changes.OrganizeType;
import Changes.Search;
import Changes.SubscriptionItem;
import Exceptions.TweeterException;
import GUI.RootGUI;
import GUI.SubscriptionsViewer;
import GUI.T_Main;
import GUI.TimelinesViewer;
import Timelines.SearchTimeline;
import Timelines.UserTimeline;
import Twitter.Tweeter;

public class ButtonManager {
	private SubscriptionsManager subscriptionsMgr;
	private TimelinesManager	 timelinesMgr;

	public ButtonManager(RootGUI gui)
	{
	    subscriptionsMgr = new SubscriptionsManager("src/subscriptionlist.xml", this, gui);
	    timelinesMgr     = new TimelinesManager(subscriptionsMgr, gui);
	}
	
	public void doAddSubscriptionTweeter(String newName) {
			subscriptionsMgr.addTweeterSubscription(newName);
	}
	
	public void addDisplaySubscription(SubscriptionItem item)
	{
	    timelinesMgr.addToTimeline(item);
	}
	
	public void removeDiaplySubscription(SubscriptionItem item)
	{
	    timelinesMgr.removeFromTimeline(item);
	}
	
	public void doDeleteSubscription(SubscriptionItem item) {
		subscriptionsMgr.removeSubscription(item);
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
	}
	
	public void doRefreshTimeline() {
		timelinesMgr.refreshTimeline();
	}
	
	public void doSelectSubscription(SubscriptionItem item) {
		timelinesMgr.clearTimeline();
		timelinesMgr.addToTimeline(item);
	}
	
	public void doShowCompositeTimeline() {
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
	}
	
	public void toggle(OrganizeType type)
	{
		OrganizeType current = timelinesMgr.organizeType();	
		if (type == OrganizeType.A_Z || type == OrganizeType.Z_A)
			type = (current == OrganizeType.A_Z ? OrganizeType.Z_A : OrganizeType.A_Z);
		else
			type = (current == OrganizeType.JAN_DEC ? OrganizeType.DEC_JAN : OrganizeType.JAN_DEC);
		
		timelinesMgr.organize(type);
	}

	public void doSearch(String query) {
		System.out.println("Searching for " + query);
		//subscriptionsMgr.addSubscription(query);
		timelinesMgr.clearTimeline();
		subscriptionsMgr.addSubscription(new Search(query));
		timelinesMgr.addSearchToTimeline(query);
	}
	
	public void systemShutdown()
	{
		timelinesMgr.deletePreviousTimelines();
		timelinesMgr.saveTimelines();
	}

}
