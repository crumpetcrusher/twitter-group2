//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : SubscriptionsViewer
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class displays the Subscriptions list in our application. It is an extension of a JPanel that houses a
// JScrollPane with stacked SubscriptionItemViewer objects. On creation, it receives an input of a
// SubscriptionsManager to manage which objects it should be displaying when the user adds a new subscription,
// searches for tweets, or deletes a subscription/serach query from the subscriptions list.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Changes.SubscriptionItem;
import backend.ButtonManager;
import backend.SubscriptionsManager;

public class SubscriptionsViewer extends JPanel {

    // There are four objects used to display the Subscriptions information to the user
    //
    // _subscriptMgr            : A reference to a SubscriptionsManager object we pass to this class to keep track
    //                            of the subscriptions the user is subscribed to
    //
    // _buttonMgr               : A reference to a ButtonManager object we pass to this class to allow any
    //                            component to access methods needed to manipulate the TimelineViewer and
    //                            SubscriptionsManager
    //
    // subscriptionItemsPanel   : A Container to hold each individual container that displays subscription
    //                            information.
    //
    // subscriptionItems        : An array of SubscriptionItemViewers to store the subscription components
    //
    //
    private static final long                 serialVersionUID  = -2630564832490907193L;
    private SubscriptionsManager              _subscriptMgr;
    private ButtonManager                     _buttonMgr;
    private Container                         subscriptionItemsPanel;
    private ArrayList<SubscriptionItemViewer> subscriptionItems = new ArrayList<SubscriptionItemViewer>();

    public SubscriptionsViewer(SubscriptionsManager subscriptMgr, ButtonManager buttonMgr) {
        
        // Assign the local values to their incoming counterparts.
        //
        _subscriptMgr = subscriptMgr;
        _buttonMgr = buttonMgr;

        // Create an Items panel to house all of the subscription items we have.
        //
        subscriptionItemsPanel = Box.createVerticalBox();

        // Create a scroll pane to house that previously mentioned items panel.
        //
        final JScrollPane scrollPane = new JScrollPane(subscriptionItemsPanel);
        scrollPane.setViewportView(subscriptionItemsPanel);

        // Add the scrollpane to this JFrame (SubscriptionsViewer itself)
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to retrieve which SubscriptionItemViewers are selected when we are building a 
    // composite timeline.  Iterates through the array of Subscriptions and finds which ones are selected.
    // 
    public SubscriptionItemViewer[] getSelected() {
        ArrayList<SubscriptionItemViewer> temp = new ArrayList<SubscriptionItemViewer>();
        for (SubscriptionItemViewer subscriptionItem : subscriptionItems) {
            if (subscriptionItem.isSelected()) {
                temp.add(subscriptionItem);
            }
        }
        SubscriptionItemViewer[] temp2 = new SubscriptionItemViewer[temp.size()];
        temp.toArray(temp2);
        return temp2;
    }

    // Method to refresh the subscriptions viewer with any changes that happen in the backend of the program.
    //
    public void refresh() {
        
        // We need to remove all subscriptionItems previously referenced since SubscriptionsManager 
        // will deal with what subscriptions we have available upon each need of a refresh.
        //
        subscriptionItemsPanel.removeAll();
        subscriptionItems.clear();
        
        // Iterate through the array of subscriptions we have available and add a new
        // subscriptionItemViewer for each one.
        //
        for (SubscriptionItem subscriptItem : _subscriptMgr.getSubscriptions()) {
            SubscriptionItemViewer subscriptionItem = new SubscriptionItemViewer(subscriptItem, _buttonMgr);
            subscriptionItemsPanel.add(subscriptionItem);
            subscriptionItems.add(subscriptionItem);

            // To stop concurrent modification issues, we create a new runnable thread to flash the 
            // SubscriptionsViewer on and off to invoke a quick refresh of the GUI components.
            //
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setVisible(false);
                    setVisible(true);
                }
            });
        }
    }
}
