package GUI;

import javax.swing.SwingUtilities;

import Twitter.SubscriptionsManager;

public class ButtonManager {

	private SubscriptionsManager subscriptionsMgr;
	private SubscriptionsViewer  subscriptionsVwr;
	private TimelineViewer		 timelineVwr;
	
	private String temp;
	
	public ButtonManager() {
		
	}
	
	public void doAddSubscription(String newName) {
		
		temp = null;
		temp = newName;
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				subscriptionsMgr.addSubscription(temp);
				subscriptionsMgr.fillCompositeTimeline();
				subscriptionsVwr.refreshSubscriptionsViewer();
				timelineVwr.refreshTimelineViewer();
				temp = null;
			}
		});
		
		
	}
	
	public void doDeleteSubscription(String name) {
		temp = null;
		temp = name;
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				subscriptionsMgr.removeSubscription(temp);
				subscriptionsMgr.fillCompositeTimeline();
				subscriptionsVwr.refreshSubscriptionsViewer();
				timelineVwr.refreshTimelineViewer();
				temp = null;
			}
		});
		
	}
	
	public void doRefreshTimeline() {
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				subscriptionsMgr.fillCompositeTimeline();
				timelineVwr.refreshTimelineViewer();
			}
		});
	}
	
	public void doSelectUser(String name) {
		temp = null;
		temp = name;
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				subscriptionsMgr.clearTimeline();
				subscriptionsMgr.addUserToTimeline(temp);
				timelineVwr.refreshTimelineViewer();
				temp = null;
			}
		});
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
