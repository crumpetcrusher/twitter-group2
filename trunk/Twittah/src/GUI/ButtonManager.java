package GUI;

import Twitter.SubscriptionsManager;

public class ButtonManager {

	private SubscriptionsManager subscriptionsMgr;
	private SubscriptionsViewer  subscriptionsVwr;
	private TimelineViewer		 timelineVwr;
	
	public ButtonManager() {
		
	}
	
	public void doAddSubscription(String newName) {
		subscriptionsMgr.addSubscription(newName);
		subscriptionsMgr.fillCompositeTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelineVwr.refreshTimelineViewer();
		
		
		
	}
	
	public void doDeleteSubscription(String name) {
		subscriptionsMgr.removeSubscription(name);
		subscriptionsMgr.fillCompositeTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelineVwr.refreshTimelineViewer();
		
		
	}
	
	public void doRefreshTimeline() {
		subscriptionsMgr.fillCompositeTimeline();
		timelineVwr.refreshTimelineViewer();
	}
	
	public void doSelectUser(String name) {
		
		subscriptionsMgr.clearTimeline();
		subscriptionsMgr.addUserToTimeline(name);
		timelineVwr.refreshTimelineViewer();
		
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
