package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Twitter.Tweeter;

public class SubscriptionItemViewer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3820782173640263639L;
	private ButtonManager buttonMgr;
	private String tweeterName;
	private ImageIcon tweeterIcon;
	private boolean itemIsSearch = false;

	private JLabel subscriptionItem = new JLabel();

	
	SubscriptionItemViewer(Tweeter newTweeter, ButtonManager newButtonMgr) {
		
		
		buttonMgr = newButtonMgr;
		tweeterName = newTweeter.getUserName();
		tweeterIcon = newTweeter.getUserPicture();
		
		setPreferredSize(new Dimension(200, 60));

		JButton button = new JButton(tweeterIcon);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(tweeterName);
						buttonMgr.doSelectUser(tweeterName);

					}
				});
		
		add(button);
		subscriptionItem.setText(tweeterName);
		
		add(subscriptionItem);
	}
	
	
	SubscriptionItemViewer(String search, ButtonManager newButtonMgr) {
		
		itemIsSearch = true;
		buttonMgr = newButtonMgr;
		tweeterName = search;
		tweeterIcon = new ImageIcon("src/Changes/twittericon.png");
		
		setPreferredSize(new Dimension(200, 60));
		
		JButton button = new JButton(tweeterIcon);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(tweeterName);
						buttonMgr.doSelectUser(tweeterName);

					}
				});
		
		add(button);
		subscriptionItem.setText(tweeterName);
		
		add(subscriptionItem);
		
		
		
	}
	
	public boolean isSearch()
	{
		return itemIsSearch;
	}
	
	
	
	
	
}
