package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private SubscriptionsManager _subscriptMgr;
	private ButtonManager _buttonMgr;
	private JButton compositeTimelineButton;
	private ArrayList<SubscriptionItemViewer> subscriptionItems = new ArrayList<SubscriptionItemViewer>();
	
	public SubscriptionsViewer(SubscriptionsManager subscriptMgr, ButtonManager buttonMgr) {
	        _subscriptMgr = subscriptMgr;
	        _buttonMgr = buttonMgr;
	        /*
		compositeTimelineButton = new JButton("Composite Timeline");
		
		compositeTimelineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						_buttonMgr.doShowCompositeTimeline(checkedSubscriptions());
					}
				});
		add(compositeTimelineButton, BorderLayout.NORTH);
		*/
		subscriptionItemsPanel = Box.createVerticalBox();
		subscriptionsScrollPane = new JScrollPane(subscriptionItemsPanel);
		//setBackground(Color.red);
		
		    final JScrollPane scrollPane = new JScrollPane(subscriptionItemsPanel);   
		    setLayout(new BorderLayout());  
		    scrollPane.setViewportView(subscriptionItemsPanel);
		    //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    add(scrollPane, BorderLayout.WEST);
	}
	
	public SubscriptionItemViewer[] getSelected()
	{
	    ArrayList<SubscriptionItemViewer> temp = new ArrayList<SubscriptionItemViewer>();
	    for(SubscriptionItemViewer subscriptionItem : subscriptionItems)
	        if(subscriptionItem.isSelected())
	            temp.add(subscriptionItem);
	       SubscriptionItemViewer[] temp2 = new SubscriptionItemViewer[temp.size()];
	       temp.toArray(temp2);
	       return temp2;
	}
	
	public void refresh() {
		subscriptionItemsPanel.removeAll();
		subscriptionItems.clear();
		for(SubscriptionItem subscriptItem : _subscriptMgr.getSubscriptions()) {
			SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(subscriptItem, _buttonMgr);
			subscriptionItemsPanel.add(subscriptionItem);
			subscriptionItems.add(subscriptionItem);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        setVisible(false);
                        setVisible(true);
                    }
                });
		}
	}
}
