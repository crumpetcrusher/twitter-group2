package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Twitter.Tweeter;

public class SubscriptionItemViewer extends JPanel {

	private JLabel subscriptionItem = new JLabel();
	
	SubscriptionItemViewer(Tweeter newTweeter) {
		
		setPreferredSize(new Dimension(200, 60));
		
		subscriptionItem.setIcon(newTweeter.getUserPicture());
		subscriptionItem.setText(newTweeter.getUserName());
		
		add(subscriptionItem);
	}
	
}
