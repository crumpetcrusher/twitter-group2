package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Changes.SubscriptionItem;
import backend.ButtonManager;
import backend.SubscriptionsManager;


public class SubscriptViewer extends JScrollPane {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2630564832490907193L;
    private Container subscriptionItemsPanel;
    private JScrollPane subscriptionsScrollPane;
    
    public SubscriptViewer() {
            /*
            setLayout(new BorderLayout());
            
            JButton compositeTimelineButton = new JButton("Composite Timeline");
            
            compositeTimelineButton.addActionListener(
                            new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                            buttonMgr.doShowCompositeTimeline();
                                    }
                            });
            
            add(compositeTimelineButton, BorderLayout.NORTH);*/
            
            //subscriptionItemsPanel = Box.createVerticalBox();
            //subscriptionsScrollPane = new JScrollPane(subscriptionItemsPanel);
            //setBackground(Color.red);
            
                //final JScrollPane scrollPane = new JScrollPane(subscriptionItemsPanel);  
                //scrollPane.setMinimumSize(new Dimension(200, 150));
                //scrollPane.setMaximumSize(maximumSize)
                //JPanel panel = new JPanel();  
                //panel.setLayout(new BorderLayout());  
                //panel.add(scrollPane, BorderLayout.CENTER);  
                //panel.setPreferredSize(new Dimension(200, 100));  
                //panel.setMinimumSize(new Dimension(200, 150));
                //panel.setVisible(true);
        setVisible(true);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setMinimumSize(new Dimension(200, 150));
        setPreferredSize(new Dimension(200, 100)); 
            //subscriptionsScrollPane.setMinimumSize(new Dimen)
    }
    
    public void refresh(SubscriptionsManager subscriptionsMgr, ButtonManager buttonMgr) {
            removeAll();
            for(SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions()) {
                    SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(subscriptItem, buttonMgr);
                    add(subscriptionItem);
                    add(Box.createVerticalGlue());
                    add(Box.createVerticalStrut(1));
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setVisible(false);
                    setVisible(true);
                }
            });
    }
}
