package GUI;

import Changes.OrganizeType;
import Twitter.SubscriptionsManager;

public class ButtonManager {

	private SubscriptionsManager subscriptionsMgr;
	private SubscriptionsViewer  subscriptionsVwr;
	private TimelineViewer		 timelineVwr;
	
	
	public ButtonManager() {
		
	}
	
	public void doAddSubscription(String newName) {
		
		subscriptionsVwr.setEnabled(false);
		subscriptionsMgr.addSubscription(newName);
		subscriptionsMgr.fillCompositeTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelineVwr.refreshTimelineViewer();
		subscriptionsVwr.setEnabled(true);
		
		
	}
	
	public void doDeleteSubscription(String name) {

		subscriptionsVwr.setEnabled(false);
		timelineVwr.setEnabled(false);
		subscriptionsMgr.removeSubscription(name);
		subscriptionsMgr.fillCompositeTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);
		subscriptionsVwr.setEnabled(true);
		
	}
	
	public void doRefreshTimeline() {
		
		timelineVwr.setEnabled(false);
		subscriptionsMgr.fillCompositeTimeline();
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);
		
	}
	
	public void doSelectUser(String name) {
		
		timelineVwr.setEnabled(false);
		subscriptionsMgr.clearTimeline();
		subscriptionsMgr.addUserToTimeline(name);
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);

	}
	
	public void sortByDate() {
		timelineVwr.setEnabled(false);
		System.out.println("sorting by date");
		OrganizeType type = OrganizeType.JAN_DEC;
		subscriptionsMgr.setOrganizeType(type);
		subscriptionsMgr.getCompositeTimeline().organize();
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);
	} 
	
	public void sortByAscend() {
		timelineVwr.setEnabled(false);
		System.out.println("sorting by ascending alphabetically");
		OrganizeType type = OrganizeType.Z_A;
		subscriptionsMgr.setOrganizeType(type);
		subscriptionsMgr.getCompositeTimeline().organize();
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);
	}
	
	public void sortByDescend() {
		timelineVwr.setEnabled(false);
		System.out.println("sorting by descending alphabetically");
		OrganizeType type = OrganizeType.A_Z;
		subscriptionsMgr.setOrganizeType(type);
		subscriptionsMgr.getCompositeTimeline().organize();
		timelineVwr.refreshTimelineViewer();
		timelineVwr.setEnabled(true);
		
	}
	
	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	public void setSubscriptionsViewer(SubscriptionsViewer newSubscriptionsVwr) {
		subscriptionsVwr = newSubscriptionsVwr;
	}
	
	public void setTimelineViewer(TimelineViewer newTimelineVwr) {
		timelineVwr = newTimelineVwr;
	}
	
}
