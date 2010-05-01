//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : RootGUI
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class displays the entire GUI and is the true starting point for the application.  A JMenubar,
// SubscriptionsViewer, TimelinesViewer, and ButtonPanel are loaded into a BorderLayout so you can view 
// Subscriptions on the left, see your Timelines on the right, and perform options with the buttons at 
// the bottom of the program and with each subscription you see in your Subscriptions List.  
//
// KNOWN LIMITATIONS
// Swing.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import Changes.OrganizeType;
import backend.ButtonManager;

public class RootGUI extends JPanel {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // There are Seven objects used to store/display the data for the RootGUI class
    //
    // buttonPanel              : 
    //
    // addSubscriptionButton    : A JButton that deals with adding a new subscription to our subscriptions list
    //
    // refreshTimelineButton    : A JButton that deals with refreshing the current timeline(s) that is(are) 
    //                            being displayed in the Timelines Viewer.
    //
    // searchButton             : A JButton that deals with searching twitter for tweets pertaining to the query
    //                          : that a user enters.
    //
    // buttonMgr                : A reference to a new ButtonManager that handles all the actions a 
    //                            user can perform.
    //
    // refreshTime              : The duration that the refreshTimeline thread should wait before the next
    //                          : refresh of the timeline.
    //
    // refreshAuto              : A JCheckBoxMenuItem for displaying the method to turn on/off the automatic
    //                          : refresh thread in the program.
    //
    private static final long serialVersionUID = 299968885467711950L;
    private JPanel            buttonPanel;
    private JButton           addSubscriptionButton;
    private JButton           refreshTimelineButton;
    private JButton           searchButton;
    private ButtonManager     buttonMgr;
    private JCheckBoxMenuItem refreshAuto;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructor
    //
    
    // No need for much explination, this is where the RootGUI object is created and passed along to other 
    // _Managers for manipulation of the GUI objects.
    //
    public RootGUI() {

        // Create a JPanel for housing all of our buttons.  Set it to a gridlayout of one row, infinite columns.
        // (just in case we would ever add another button)
        //
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 0));
        setLayout(new BorderLayout());
        
        // Pass this RootGUI reference into ButtonManager and create a reference to buttonManager for the
        // menu options we have on the RootGUI pane.  Passing a reference of the RootGUI to ButtonManager
        // allows for it to create/refresh/delete objects from the RootGUI whenever it's called.
        //
        buttonMgr = new ButtonManager(this);
        
        // JButton for adding a new subscription.
        //
        addSubscriptionButton = new JButton("Add Subscription");
        buttonPanel.add(addSubscriptionButton);
        
        // ActionListener for our add subscription button.  Calls ButtonManager to add a subscription based on
        // the username entered through a JDialog.  If no information is entered, return back to the main screen.
        //
        addSubscriptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Username:");
                if ((name == null) || (name == "")) {
                    System.out.println("no user entered");
                }
                else {
                    buttonMgr.doAddSubscriptionTweeter(name);

                }
            }
        });
        
        // JButton for refreshing.  One-click, and away it goes.  Refreshes whatever subscriptions are
        // selected for viewing at the current time.
        //
        refreshTimelineButton = new JButton("Refresh Timeline");
        buttonPanel.add(refreshTimelineButton);
        
        // ActionListener for our refresh timeline button.  Calls ButtonManager to refresh the timeline(s)
        // currently being displayed through TimelinesViewer
        //
        refreshTimelineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonMgr.doRefreshTimeline();
            }
        });
        
        // JButton for Searching.  Ask the user through a dialog what the query is they would like to
        // serach for.  If nothing is entered, or the user presses cancel, then no action has been made at all.
        //
        searchButton = new JButton("Search");
        buttonPanel.add(searchButton);
        
        // ActionListener for our Search button.  Shows JDialog to query user for an input, then either calls
        // ButtonManager to serach for that query or closes back to the main program screen.
        //
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = JOptionPane.showInputDialog("Query: ");
                if ((query == null) || (query == "")) {
                    System.out.println("No text to search for");
                }
                else {
                    buttonMgr.doSearch(query);
                }
            }
        });
        
        // Add the buttonPanel to the bottom of the program
        //
        add(buttonPanel, BorderLayout.SOUTH);

        // The main JFrame we will be using to add this RootGUI JPanel to and display our program with.
        //
        JFrame frame = new JFrame("Twittah!");
        frame.setResizable(false);

        // Creating our menubar for view, sorting, and refreshing options.
        //
        JMenuBar menubar = new JMenuBar();
        JMenu view = new JMenu("View");
        JMenu sort = new JMenu("Sort");
        JMenu options = new JMenu("Options");
        JMenu refreshOptions = new JMenu("Refresh every...");
        ButtonGroup sortGroup = new ButtonGroup();
        options.add(refreshOptions);

        // JMenuItem for a selectable action of viewing the composite timeline.
        // Used to show all the timelines (sorted properly) of subscribers that have a checkmark by 
        // their name on the SubscriptionsViewer list.
        //
        JMenuItem viewComposite = new JMenuItem("Composite Timeline");
        view.add(viewComposite);
        
        // ActionListener for selection of the View Composite Timeline menu item.
        // Calls ButtonManager to do the showing of a composite timeline
        //
        viewComposite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonMgr.doShowCompositeTimeline();
            }
        });

        // JRadioButton for sorting the timeline by Date.  Toggles between Ascending and Descending order.
        //
        JRadioButtonMenuItem sortByDate = new JRadioButtonMenuItem("Date");
        sortGroup.add(sortByDate);
        sort.add(sortByDate);
        
        // ActionListener for selection of the SortByDate menu item
        // Calls ButtonManager to apply the timeline sorting
        //
        sortByDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonMgr.toggleOrganize(OrganizeType.JAN_DEC);
            }
        });

        // JRadioButton for sorting the timeline alphabetically.  Toggles between Ascending and Descending order.
        // Used to sort how tweets are displayed 
        //
        JRadioButtonMenuItem sortByAlphabetical = new JRadioButtonMenuItem("Alphabetical");
        sortGroup.add(sortByAlphabetical);
        sort.add(sortByAlphabetical);
        
        // ActionListener for selection of the SortByAlphabetical menu item.
        // Calls ButtonManager to apply the timeline sorting
        //
        sortByAlphabetical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonMgr.toggleOrganize(OrganizeType.A_Z);
            }
        });

        // JCheckBox for our Options menu to allow selection of refreshing the timeline automatically
        //
        refreshAuto = new JCheckBoxMenuItem("30 seconds");
        refreshAuto.setSelected(false);
        refreshOptions.add(refreshAuto);

        // ItemListener for selection of the Refresh Automatically menu item.  Used to resume and suspend the
        // thread created earlier that automatically refreshes the timelines currently being viewed.
        //
        refreshAuto.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                buttonMgr.toggleAutomaticRefresh();
            }
        });

        // Add the dropdown objects to the actual menubar.
        //
        menubar.add(view);
        menubar.add(sort);
        menubar.add(options);

        // Main properties for our JFrame.  Add the menubar, this JPanel for displaying all our objects,
        // and pack everything together.
        //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menubar);
        frame.getContentPane().add(this);
        frame.setPreferredSize(new Dimension(700, 500));
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        
        // Adding a window listener so we can save previous tweets/subscriptions and the previous sorting style
        // to disk.
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                buttonMgr.systemShutdown();
                System.exit(0);
            }
        });
    }
}
