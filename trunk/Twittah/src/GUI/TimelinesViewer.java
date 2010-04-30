//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : TimelinesViewer
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class displays the Timelines list in our application.  It is an extension of a JPanel that houses a
// JScrollPane with stacked DisplayItemViewer objects.  On creation, it receives an input of a TimelinesManager
// to manage which objects it should be displaying when the user selects an option to change the Timeline
// in any way.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import Changes.DisplayItem;
import backend.TimelinesManager;

public class TimelinesViewer extends JPanel {
    
    // There are three objects used to display the Timeline information to the user
    //
    // TimelinesMgr             : A reference to a TimelinesManager object we pass to this class to keep track
    //                            of the Tweets the user is requesting to see.
    //
    // timelineScrollPane       : A JScrollPane used to hold the timelineItemsPanel so it will be able to scroll
    //                            when the added number of tweets exceeds the vertical size of the window.
    //
    // timelineItemsPanel       : A Container to hold each individual container that displays tweet information.
    //
    private static final long serialVersionUID          = -5829050201072472719L;
    private TimelinesManager  timelinesMgr;
    private JScrollPane       timelineScrollPane        = new JScrollPane();
    private Container         timelineItemsPanel;
	
    public TimelinesViewer(TimelinesManager newTimelinesMgr) {
        
        // Assign this class a TimelinesManager so it can get any new tweet information when requested
        //
        timelinesMgr       = newTimelinesMgr;

        // Our Items Panel is a vertical box layout since it's tweets stacked on top of eachother
        //
	timelineItemsPanel = Box.createVerticalBox();

	// Add the Items Panel to the Scroll Pane
	timelineScrollPane.setViewportView(timelineItemsPanel);

	// Add the scroll pane to this JPanel with a BorderLayout and center it. This helped in making sure
	// GUI components were not overlapping or displaying incorrectly.
	//
	setLayout(new BorderLayout());
	add(timelineScrollPane, BorderLayout.CENTER);

	// Start refreshing the TimelineViewer with any tweets we have in our TimelinesManager
	//
	refresh();
    }

    // Method to refresh the timelines viewer with any changes happening in the backend of things
    //
    public void refresh() {
	
        // First, since whatever needs to be displayed to the user is handled through our TimelinesManager, we clear the 
        // timelineItemsPanel so we can receive the incoming tweets to be displayed.
        //
        timelineItemsPanel.removeAll();

        // For each tweet that was added to our CompositeTimeline in TimelinesManager, we create a new DisplayItemViewer
        // and add it to the timelineItemsPanel, housed inside the timelineScrollPanel.
        //
        for(DisplayItem tweet : timelinesMgr.getCompositeTimeline().displayItems()) {
            DisplayItemViewer displayItem = new DisplayItemViewer(tweet);
            timelineItemsPanel.add(Box.createVerticalGlue());
            timelineItemsPanel.add(displayItem);
            timelineItemsPanel.add(Box.createVerticalStrut(10));
	}

        // To stop concurrent modification issues, we create a new runnable thread to flash the TimelinesViewer on and off
        // to invoke a quick refresh of the GUI components.
        //
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setVisible(false);
                setVisible(true);
            }
        });
    }
}