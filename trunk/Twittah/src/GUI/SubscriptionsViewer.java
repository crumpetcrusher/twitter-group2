package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Twitter.Tweeter;
import backend.ButtonManager;
import backend.SubscriptionsManager;

public class SubscriptionsViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630564832490907193L;
	private SubscriptionsManager subscriptionsMgr;
	private ButtonManager		 buttonMgr;
	private Container subscriptionItemsPanel;
	private JScrollPane subscriptionsScrollPane;
	
	public SubscriptionsViewer() {
		
		setLayout(new BorderLayout());
		
		JButton compositeTimelineButton = new JButton("Composite Timeline");
		
		compositeTimelineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMgr.doShowCompositeTimeline();
					}
				});
		
		add(compositeTimelineButton, BorderLayout.NORTH);
		
		subscriptionItemsPanel = Box.createVerticalBox();
		subscriptionsScrollPane = new JScrollPane(subscriptionItemsPanel);
		
		subscriptionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(subscriptionsScrollPane, BorderLayout.CENTER);
		
	}

	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	public void setButtonManager(ButtonManager newButtonMgr) {
		buttonMgr = newButtonMgr;
	}
	
	public void refreshSubscriptionsViewer() {
		subscriptionItemsPanel.removeAll();
		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()) {
			SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(tweeter, buttonMgr);
			subscriptionItemsPanel.add(subscriptionItem);
			subscriptionItemsPanel.add(Box.createVerticalGlue());
			subscriptionItemsPanel.add(Box.createVerticalStrut(1));
		}
	}
}
