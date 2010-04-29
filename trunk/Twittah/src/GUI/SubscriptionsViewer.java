package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Changes.SubscriptionItem;
import Twitter.Tweeter;
import backend.ButtonManager;
import backend.SubscriptionsManager;

public class SubscriptionsViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630564832490907193L;
	private Container subscriptionItemsPanel;
	private JScrollPane subscriptionsScrollPane;
	
	public SubscriptionsViewer() {
		/*
		setLayout(new BorderLayout());
		
		JButton compositeTimelineButton = new JButton("Composite Timeline");
		
		compositeTimelineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMgr.doShowCompositeTimeline();
					}
				});
		                      subscriptionItemsPanel.add(Box.createVerticalGlue());
                        subscriptionItemsPanel.add(Box.createVerticalStrut(1));
		add(compositeTimelineButton, BorderLayout.NORTH);*/
		
		subscriptionItemsPanel = Box.createVerticalBox();
		subscriptionsScrollPane = new JScrollPane(subscriptionItemsPanel);
		setBackground(Color.red);
		
		    final JScrollPane scrollPane = new JScrollPane(subscriptionItemsPanel);   
		    setLayout(new BorderLayout());  
		    scrollPane.setViewportView(subscriptionItemsPanel);
		    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    add(scrollPane, BorderLayout.WEST);
	}
	
	public void refresh(SubscriptionsManager subscriptionsMgr, ButtonManager buttonMgr) {
		subscriptionItemsPanel.removeAll();
		for(SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions()) {
			SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(subscriptItem, buttonMgr);
			subscriptionItemsPanel.add(subscriptionItem);

		}
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        setVisible(false);
                        setVisible(true);
                    }
                });
	}
}
