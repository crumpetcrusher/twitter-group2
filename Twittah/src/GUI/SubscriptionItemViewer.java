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

import Twitter.Tweeter;
import backend.ButtonManager;

public class SubscriptionItemViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3820782173640263639L;
	private ButtonManager buttonMgr;
	private String tweeterName;
	private ImageIcon tweeterIcon;
	private JLabel	nameLabel;
	private JButton selectUserButton;
	private JButton selectSearchButton;
	private boolean itemIsSearch = false;

	
	SubscriptionItemViewer(Tweeter newTweeter, ButtonManager newButtonMgr) {
		
		
		buttonMgr = newButtonMgr;
	
		tweeterName = newTweeter.getUserName();
		tweeterIcon = newTweeter.getUserPicture();
		
		setPreferredSize(new Dimension(200, 40));

		selectUserButton = new JButton(tweeterIcon);
		selectUserButton.setOpaque(false);
		selectUserButton.setBorderPainted(false);
		selectUserButton.setContentAreaFilled(false);
		
		selectUserButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(tweeterName);
						buttonMgr.doSelectUser(tweeterName);
					}
				});
		
		nameLabel = new JLabel(tweeterName);
		
		add(selectUserButton, BorderLayout.EAST);
		add(nameLabel, BorderLayout.CENTER);
	}
	
	
	SubscriptionItemViewer(String search, ButtonManager newButtonMgr) {
		
		itemIsSearch = true;
		buttonMgr = newButtonMgr;
		tweeterName = search;
		tweeterIcon = new ImageIcon("src/Changes/twittericon.png");
		nameLabel = new JLabel(tweeterName);
		
		setPreferredSize(new Dimension(200, 60));
		
		selectSearchButton = new JButton(tweeterIcon);
		selectSearchButton.setOpaque(false);
		selectSearchButton.setBorderPainted(false);
		selectSearchButton.setContentAreaFilled(false);
		
		selectSearchButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(tweeterName);
						buttonMgr.doSelectUser(tweeterName);

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
