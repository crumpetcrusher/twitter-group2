package backend;

import java.awt.Container;

import Changes.OrganizeType;
import GUI.SubscriptionsViewer;
import GUI.TimelinesViewer;

public class ButtonManager {

	private SubscriptionsManager subscriptionsMgr;
	private SubscriptionsViewer  subscriptionsVwr;
	private TimelinesManager	 timelinesMgr;
	private TimelinesViewer		 timelinesVwr;
	
	private Container 			 rootPane;
	
	
	public ButtonManager() {
		
	}
	
	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	public void setSubscriptionsViewer(SubscriptionsViewer newSubscriptionsVwr) {
		subscriptionsVwr = newSubscriptionsVwr;
	}
	
	public void setTimelinesManager(TimelinesManager newTimelinesMgr) {
		timelinesMgr = newTimelinesMgr;
	}
	public void setTimelinesViewer(TimelinesViewer newTimelinesVwr) {
		timelinesVwr = newTimelinesVwr;
	}
	
	public void setRootPane(Container newRootPane) {
		rootPane = newRootPane;
	}
	public void doAddSubscription(String newName) {
		
		
		//subscriptionsVwr.setEnabled(false);
		//timelinesVwr.setEnabled(false);
		subscriptionsMgr.addSubscription(newName);
		timelinesMgr.clearTimeline();
		timelinesMgr.addUserToTimeline(newName);
		timelinesMgr.refreshTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		subscriptionsVwr.repaintSubscriptionsViewer();
		timelinesVwr.repaintTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//subscriptionsVwr.setVisible(false);
		//subscriptionsVwr.setVisible(true);
		//subscriptionsVwr.setEnabled(true);
		//timelinesVwr.setEnabled(true);
		
		
	}
	
	public void doDeleteSubscription(String name) {

		//subscriptionsVwr.setEnabled(false);
		//timelinesVwr.setEnabled(false);
		subscriptionsMgr.removeSubscription(name);
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		subscriptionsVwr.repaintSubscriptionsViewer();
		timelinesVwr.repaintTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//subscriptionsVwr.setVisible(false);
		//subscriptionsVwr.setVisible(true);
		//subscriptionsVwr.setEnabled(true);
		//timelinesVwr.setEnabled(true);
		
	}
	
	public void doRefreshTimeline() {
		
		//timelinesVwr.setEnabled(false);
		timelinesMgr.refreshTimeline();
		timelinesVwr.refreshTimelinesViewer();
		timelinesVwr.repaintTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//timelinesVwr.setEnabled(true);
		
	}
	
	public void doSelectUser(String name) {
		
		timelinesMgr.clearTimeline();
		timelinesMgr.addUserToTimeline(name);
		timelinesVwr.refreshTimelinesViewer();
		timelinesVwr.repaintTimelinesViewer();
		rootPane.repaint();

	}
	
	public void doShowCompositeTimeline() {
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
		timelinesVwr.refreshTimelinesViewer();
		timelinesVwr.repaintTimelinesViewer();
		rootPane.repaint();
	}
	
	public void sortByDate() {
		//timelinesVwr.setEnabled(false);
		System.out.println("sorting by date");
		OrganizeType type = OrganizeType.JAN_DEC;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//timelinesVwr.setEnabled(true);
	} 
	
	public void sortByAscend() {
		//timelinesVwr.setEnabled(false);
		System.out.println("sorting by ascending alphabetically");
		OrganizeType type = OrganizeType.Z_A;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//timelinesVwr.setEnabled(true);
	}
	
	public void sortByDescend() {
		//timelinesVwr.setEnabled(false);
		System.out.println("sorting by descending alphabetically");
		OrganizeType type = OrganizeType.A_Z;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		rootPane.repaint();
		//timelinesVwr.setVisible(false);
		//timelinesVwr.setVisible(true);
		//timelinesVwr.setEnabled(true);
		
	}
	
	public void search(String query)
	{
		timelinesVwr.setEnabled(false);
		System.out.println("Searching for " + query);
		timelinesMgr.addSearchToTimeline(query);
		timelinesVwr.refreshTimelinesViewer();
		timelinesVwr.setEnabled(true);
	}
	
}
