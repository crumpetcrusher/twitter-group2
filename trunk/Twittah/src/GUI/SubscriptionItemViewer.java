//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : SubscriptionItemViewer
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class is an extended JPanel used for displaying the actual Subscription information for a certain 
// subscriber.  It takes in a ButtonManager to deal with the user selection and also takes in a
// SubscriptionItem with all the necessary information about the subscription and creates
// a JFrame object to add to our SubscriptionsViewer JScrollPane for actual viewing.
// 
//
// KNOWN LIMITATIONS
// A long subscription name will be clipped off due to the size of the subscriptions viewer and JLabels having 
// no wrap.
// 
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Changes.SubscriptionItem;
import Timelines.Timeline;
import backend.ButtonManager;

public class SubscriptionItemViewer extends JPanel {

    // There are eleven objects used to display the SubscriptionItem information to the user
    //
    // buttonMgr                 : A reference to a ButtonManager object we pass to this class to allow
    //                            manipulation of the GUI.
    //                            
    //
    // id                        : A string that holds the Tweeter Username (not real name) for display.
    //
    // icon                      : An ImageIcon that holds the profile picture of the subscription item.
    // 
    // viewIcon                  : An ImageIcon of an eye that is used for the view button.  From the 
    //                           : "Silk" icon set at famfamfam.com.  Used with permission??
    //
    // bombIcon                  : An ImageIcon of a bomb that is used for the delete button.  From the 
    //                           : "Silk" icon set at famfamfam.com.  Used with permission??
    //
    // iconAndName               : A JLabel that holds icon and id for easy adding to a borderlayout.
    //
    // deleteButton              : A JButton that uses bombIcon and deletes a user from the subscriptions.
    //
    // viewButton                : A JButton that uses viewIcon and displays the subscription of the
    //                           : subscriptionItem that was clicked by using the viewButton.
    //
    // _subscriptItem            : A SubscriptionItem that is used to hold the subscription information
    //                           : for displaying usage.
    //
    // selectSubscriptionCheckBox: A JCheckBox used when selecting View->Composite Timeline in the menubar.
    //                           : Its' status of checked/unchecked is used for creating a CompositeTimeline
    //                           : based on which subscriptions are checked when selecting view composite
    //                           : timeline.
    //
    private static final long serialVersionUID = -3820782173640263639L;
    private ButtonManager     buttonMgr;
    private String            id;
    private ImageIcon         icon;
    private ImageIcon         viewIcon         = new ImageIcon("src/icons/eye.png");
    private ImageIcon         bombIcon         = new ImageIcon("src/icons/bomb.png");
    private JLabel            iconAndName;
    private JButton           deleteButton;
    private JButton           viewButton;
    private SubscriptionItem  _subscriptItem;
    private JCheckBox         selectSubscriptionCheckBox;

    SubscriptionItemViewer(SubscriptionItem subscriptItem, ButtonManager newButtonMgr) {

        // Set the incoming items to their respectful variables above for usage.
        //
        _subscriptItem  = subscriptItem;
        buttonMgr       = newButtonMgr;
        icon            = subscriptItem.icon();
        id              = "  " + subscriptItem.text();
        iconAndName     = new JLabel(id);
        iconAndName.setIcon(icon);
        iconAndName.setIconTextGap(2);

        // The size needed for this subscription viewer to properly be displayed.
        //
        setPreferredSize(new Dimension(200, 80));
        setMaximumSize(new Dimension(200, 80));

        // Create the view and delete buttons to either view the timeline of the selected user 
        // or delete the subscription from our program.
        deleteButton = new JButton(bombIcon);
        viewButton = new JButton(viewIcon);

        // Create the checkbox used when asking to see the composite timeline.  This gets referenced in 
        // the ButtonManager when View -> Composite Timeline is selected and it looks for which subscriptions have
        // been checked for viewing.
        //
        selectSubscriptionCheckBox = new JCheckBox();
        selectSubscriptionCheckBox.setSelected(true);

        // Create JPanel holders for our buttons and the information about the subscription
        // 
        JPanel buttonHolder = new JPanel(new GridLayout(1, 0));
        JPanel infoHolder = new JPanel(new BorderLayout());
        
        // Add the view and delete buttons to the buttonHolder panel
        //
        buttonHolder.add(viewButton);
        buttonHolder.add(deleteButton);

        // Add the select subscription check box, the icon, name, and buttonHolder to the container for
        // holding all the info about the subscription 
        //
        infoHolder.add(selectSubscriptionCheckBox, BorderLayout.WEST);
        infoHolder.add(iconAndName, BorderLayout.CENTER);
        infoHolder.add(buttonHolder, BorderLayout.SOUTH);

        // ActionListener for the view button.  Calls upon buttonManager to show the subscription in 
        // SubscriptionsManager by passing the subscriptionItem referenced in each ItemViewer
        //
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(_subscriptItem.text());
                buttonMgr.doSelectSubscription(_subscriptItem);
            }
        });

        // ActionListener for the delete button.  Calls upon buttonManager to delete the subscription
        // in SubscriptionsManager by passing the subscriptionItem referenced in each itemViewer and 
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonMgr.doDeleteSubscription(_subscriptItem);
            }
        });

        // Set the layout for our JPanel, which doesn't have to be, but making it borderlayout
        // and centering everything helps with overlapping and general GUI glitches.
        //
        setLayout(new BorderLayout());
        add(infoHolder, BorderLayout.CENTER);
    }

    // Method to retrieve the state of the select subscription checkbox for
    // creating a compositeTimeline based on who is selected
    //
    public boolean isSelected() {
        return selectSubscriptionCheckBox.isSelected();
    }

    // Method to retrieve the timeline for the subscription item viewer that gets called
    //
    public Timeline timeline() {
        return _subscriptItem.timeline();
    }

    // Method to set the select subscription checkbox to deselected when needed
    //
    public void unCheck() {
        selectSubscriptionCheckBox.setSelected(false);
    }

}
