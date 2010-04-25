package backend;

import java.awt.Container;

import javax.swing.SwingUtilities;

import Changes.OrganizeType;
import GUI.SubscriptionsViewer;
import GUI.TimelinesViewer;

public class ButtonManager {
	private SubscriptionsManager subscriptionsMgr;
	private SubscriptionsViewer  subscriptionsVwr;
	private TimelinesManager	 timelinesMgr;
	private TimelinesViewer		 timelinesVwr;

	public ButtonManager() { }
	
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
	
	public void doAddSubscription(String newName) {
		subscriptionsMgr.addSubscription(newName);
		timelinesMgr.clearTimeline();
		timelinesMgr.addUserToTimeline(newName);
		timelinesMgr.refreshTimeline();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	subscriptionsVwr.setVisible(false);
		    	timelinesVwr.setVisible(false);
		    	subscriptionsVwr.setVisible(true);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void doDeleteSubscription(String name) {
		subscriptionsMgr.removeSubscription(name);
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	subscriptionsVwr.setVisible(false);
		    	timelinesVwr.setVisible(false);
		    	subscriptionsVwr.setVisible(true);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void doRefreshTimeline() {
		timelinesMgr.refreshTimeline();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void doSelectUser(String name) {
		timelinesMgr.clearTimeline();
		timelinesMgr.addUserToTimeline(name);
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void doShowCompositeTimeline() {
		timelinesMgr.clearTimeline();
		timelinesMgr.initialize();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void sortByDate() {
		System.out.println("sorting by date");
		OrganizeType type = OrganizeType.JAN_DEC;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	} 
	
	public void sortByAscend() {
		System.out.println("sorting by ascending alphabetically");
		OrganizeType type = OrganizeType.Z_A;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void sortByDescend() {
		System.out.println("sorting by descending alphabetically");
		OrganizeType type = OrganizeType.A_Z;
		timelinesMgr.setOrganizeType(type);
		timelinesMgr.getCompositeTimeline().organize();
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
	
	public void doSearch(String query) {
		System.out.println("Searching for " + query);
		timelinesMgr.clearTimeline();
		timelinesMgr.addSearchToTimeline(query);
		timelinesVwr.refreshTimelinesViewer();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	timelinesVwr.setVisible(false);
		    	timelinesVwr.setVisible(true);
		    }
		});
	}
}
