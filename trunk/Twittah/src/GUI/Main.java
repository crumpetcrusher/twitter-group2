package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Twitter.SubscriptionsManager;

public class Main extends JPanel{

	private SubscriptionsManager subscriptionsMgr;
	
	private SubscriptionsViewer	 subscriptionsVwr;
	private TimelineViewer		 timelineVwr;
	private ButtonManager		 buttonMgr;
	
	private JPanel				 buttonPanel;
	private JButton				 addSubscriptionButton;
	private JButton				 deleteSubscriptionButton;
	private JButton				 refreshTimelineButton;
	
	
	/**
	 * GUI creation
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	public Main() throws Exception
	{

		setLayout(new BorderLayout());
		
		subscriptionsMgr = new SubscriptionsManager("src/subscriptionlist.xml");
		buttonMgr 		 = new ButtonManager();
		subscriptionsVwr = new SubscriptionsViewer();
		timelineVwr		 = new TimelineViewer();
		
		
		
		buttonMgr.setSubscriptionsManager(subscriptionsMgr);
		buttonMgr.setSubscriptionsViewer(subscriptionsVwr);
		buttonMgr.setTimelineViewer(timelineVwr);
		
		
		
		subscriptionsVwr.setSubscriptionsManager(subscriptionsMgr);
		subscriptionsVwr.refreshSubscriptionsViewer();
		
		
		add(subscriptionsVwr, BorderLayout.WEST);
		
		timelineVwr.setSubscriptionsManager(subscriptionsMgr);
		timelineVwr.refreshTimelineViewer();
		
		add(timelineVwr, BorderLayout.EAST);
		
		
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(1,2));
		
		addSubscriptionButton = new JButton("Add Subscription");
		
		addSubscriptionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter Username:");
						if (name == null){
							System.out.println("no user entered");
						}
						else{
							buttonMgr.doAddSubscription(name);
						}
					}
				});
		
		buttonPanel.add(addSubscriptionButton);
		
		deleteSubscriptionButton = new JButton("Delete Subscription");
		
		deleteSubscriptionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter username:");
						if (name == null){
							System.out.println("no user entered");
						}
						else{
							buttonMgr.doDeleteSubscription(name);
						}
					}
				});

		buttonPanel.add(deleteSubscriptionButton);
		
		refreshTimelineButton = new JButton("Refresh Timeline");
		
		refreshTimelineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							buttonMgr.doRefreshTimeline();
					}
				});
		
		buttonPanel.add(refreshTimelineButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		
        
	}
	
}
