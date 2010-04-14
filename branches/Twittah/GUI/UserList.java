package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Twitter.Tweeter;

public class UserList extends JPanel {

	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	
	public UserList(ArrayList<Tweeter> subscribedTweeters) {
	
		// Subscriptions List
		//
		String [] subscribers = new String[subscribedTweeters.size()];
	
		for (int i = 0; i < subscribedTweeters.size(); i++)
		{
			subscribers[i] = subscribedTweeters.get(i).getScreenName();
			System.out.println(subscribers[i]);
		}

		JList subscriptionsList = new JList(subscribers);
		subscriptionsList.setVisibleRowCount(4);
		
		// Subscriptions Management
		//
		Container subscriptionsManagement = Box.createVerticalBox();
		subscriptionsManagement.add(addButton);
		subscriptionsManagement.add(deleteButton);
	
		// Add button events
		//
		addButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Dialog box to add new user to subscriptions list
					//
					
				}
			}
		);
		
		
		// Delete button events
		//
		deleteButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// get selected user from JList and delete them
					// (should be method in subscriptions.java
					//
				
				}
			}
		);
	
	
		// Subscriptions Viewer
		//
		setLayout(new BorderLayout());
		add(new JLabel("Subscriptions"), BorderLayout.NORTH);
		add(subscriptionsList, BorderLayout.CENTER);
		add(subscriptionsManagement, BorderLayout.SOUTH);
		
	}
}
