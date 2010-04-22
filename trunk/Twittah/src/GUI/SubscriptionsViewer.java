package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Twitter.SubscriptionsManager;
import Twitter.Tweeter;

public class SubscriptionsViewer extends JPanel {
	
	private SubscriptionsManager subscriptionsMgr;
	
	private JPanel subscriptionItemsPanel;
	private JScrollPane subscriptionsScrollPane;
	
	public SubscriptionsViewer(){
		
		subscriptionItemsPanel = new JPanel();
		subscriptionsScrollPane = new JScrollPane();
		
		subscriptionItemsPanel.setLayout(new GridLayout(0, 1));
		
		subscriptionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//subscriptionsScrollPane.setPreferredSize(new Dimension(200, 500));
		subscriptionsScrollPane.setViewportView(subscriptionItemsPanel);
		
		add(subscriptionsScrollPane);
		
	}
	
	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	public void refreshSubscriptionsViewer() {
		
		subscriptionItemsPanel.removeAll();
		
		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()) {
			SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(tweeter);
			subscriptionItemsPanel.add(subscriptionItem);
		}
		
		subscriptionsScrollPane.setVisible(false);
		subscriptionsScrollPane.setVisible(true);
		
		
		
	}
	
	
	

}
