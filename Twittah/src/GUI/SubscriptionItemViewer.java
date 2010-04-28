package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Changes.SubscriptionItem;
import Twitter.Tweeter;
import backend.ButtonManager;

public class SubscriptionItemViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3820782173640263639L;
	private ButtonManager buttonMgr;
	private String id;
	private ImageIcon icon;
	private JLabel	nameLabel;
	private JButton selectSubscriptionButton;
	private JButton selectSearchButton;
	private JButton deleteButton;
	private JButton viewButton;
	private SubscriptionItem _subscriptItem;
	private boolean itemIsSearch = false;

	
	SubscriptionItemViewer(SubscriptionItem subscriptItem, ButtonManager newButtonMgr) {
		
	        _subscriptItem = subscriptItem;
		buttonMgr = newButtonMgr;
		deleteButton = new JButton("X");
		viewButton = new JButton("View");
	
		id = subscriptItem.text();
		icon = subscriptItem.icon();
		
		setPreferredSize(new Dimension(200, 40));

		selectSubscriptionButton = new JButton(icon);
		selectSubscriptionButton.setOpaque(false);
		selectSubscriptionButton.setBorderPainted(false);
		selectSubscriptionButton.setContentAreaFilled(false);
		
		selectSubscriptionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMgr.doSelectSubscription(_subscriptItem);
					}
				});
		
		deleteButton.addActionListener(
                              new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                              buttonMgr.doDeleteSubscription(_subscriptItem);
                                      }
                              });
		
		nameLabel = new JLabel(id);
		
		add(selectSubscriptionButton, BorderLayout.EAST);
		add(nameLabel, BorderLayout.CENTER);
		add(deleteButton, BorderLayout.EAST);
		add(viewButton, BorderLayout.EAST);
	}
	
	
	SubscriptionItemViewer(String search, ButtonManager newButtonMgr) {
		
		itemIsSearch = true;
		buttonMgr = newButtonMgr;
		id = search;
		icon = new ImageIcon("src/Changes/twittericon.png");
		nameLabel = new JLabel(id);
		
		setPreferredSize(new Dimension(200, 60));
		
		selectSearchButton = new JButton(icon);
		selectSearchButton.setOpaque(false);
		selectSearchButton.setBorderPainted(false);
		selectSearchButton.setContentAreaFilled(false);
		
		selectSearchButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonMgr.doSelectSubscription(_subscriptItem);

					}
				});
		
		add(selectSearchButton, BorderLayout.EAST);
		
		
		add(nameLabel, BorderLayout.CENTER);
		
		
		
	}
	
	public boolean isSearch()
	{
		return itemIsSearch;
	}
	
	
	
	
	
}
