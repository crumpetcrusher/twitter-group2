package GUI;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Changes.DisplayItem;
import Twitter.SubscriptionsManager;

/**
 * Displays all of the TweetContainer GUI objects in a "Timeline" feed
 * @author Frappe051
 *
 */
public class TimelineViewer extends JPanel
{
	private SubscriptionsManager subscriptionsMgr;
	private JPanel timelineItemsPanel;
	private JScrollPane timelineScrollPane;
	
	public TimelineViewer() {
		
		timelineItemsPanel = new JPanel();
		timelineScrollPane = new JScrollPane();
		
		timelineItemsPanel.setLayout(new GridLayout(0, 1));
		
		timelineScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		timelineScrollPane.setPreferredSize(new Dimension(500, 500));
		timelineScrollPane.setViewportView(timelineItemsPanel);
		
		add(timelineScrollPane);
	}
	
	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	public void refreshTimelineViewer() {
		
		timelineItemsPanel.removeAll();
		
		for(DisplayItem tweet : subscriptionsMgr.getCompositeTimeline().displayItems()) {
			DisplayItemViewer displayItem = new DisplayItemViewer(tweet);
			timelineItemsPanel.add(displayItem);
		}
		
		timelineScrollPane.setVisible(false);
		timelineScrollPane.setVisible(true);
		
		
		
	}
	
	

}