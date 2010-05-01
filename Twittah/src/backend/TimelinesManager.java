//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              Ê: IST240 - Twitter Application
//
// Class Name            : TimelinesManager
//    
// Authors               : Scott Smiesko, Rick Humes
// Date                  : 2010-30-04
//
//
// DESCRIPTION
// This class handles the management of timelines that are being displayed to the user.  It holds an object
// of type CompositeTimeline which stores multiple timelines which can be sorted by the users choice.  It
// is the object that gets called to manipulate the TimelinesViewer based on what subscriptionsManager says 
// needs to be being displayed at the current time.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;

import Changes.OrganizeType;
import Changes.SubscriptionItem;
import GUI.RootGUI;
import GUI.TimelinesViewer;
import ThreadingHelpers.ProgramState;
import ThreadingHelpers.ProgramStateEvent;
import ThreadingHelpers.ProgramStateListener;
import Timelines.CompositeTimeline;
import Timelines.SearchTimeline;
import Timelines.Timeline;
import Twitter.Tweeter;

public class TimelinesManager implements ProgramStateListener {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class holds 4 attributes which aid in the viewing of a composite timeline in the GUI
    // 
    // subscriptionsMgr                 :  The reference for our subscriptions manager; this class uses it to 
    //                                     pull subscriptions from our database of subscriptions and update the
    //                                     composite timeline with whoever we need to display at a given time.
    //
    // timelinesVwr                     :  The reference for our timelinesViewer GUI object.  This is used for 
    //                                     TimelinesManager to be able to display the current feeds available in
    //                                     its compositeTimeline attribute.
    //
    // compositeTimeline                :  A subclass of Timeline to store multiple timelines for viewing in
    //                                     the GUI with timelinesVwr.
    //
    // refresher                        :  An extension of a Thread. used for automatically refreshing the 
    //                                     timelines currently in the compositeTimeline
    //
    //
    private SubscriptionsManager       subscriptionsMgr;
    private TimelinesViewer            timelinesVwr;
    private CompositeTimeline          compositeTimeline = new CompositeTimeline();
    private AutomaticTimelineRefresher refresher         = new AutomaticTimelineRefresher(compositeTimeline, 30);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // This constructor is passed in the subscriptionsManager and RootGUI and creates the necessary environment
    // for compositeTimeline to work and the timelinesViewer to display the timelines
    //
    public TimelinesManager(SubscriptionsManager newSubscriptionsMgr, RootGUI gui) {
        compositeTimeline.addProgramStateListener(this);
        timelinesVwr = new TimelinesViewer(this);
        subscriptionsMgr = newSubscriptionsMgr;
        loadPreviousTimelines();
        gui.add(timelinesVwr);
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // This is used to initialize..
    //
    protected void initialize() {
        for (SubscriptionItem subscriptItem : subscriptionsMgr.getSubscriptions())
            addToTimeline(subscriptItem);
    }

    // This is to toggle the automatic refresh of the composite timeline
    //
    public void toggleAutomaticRefresh() {
        if (refresher.isPaused())
            refresher.Resume();
        else
            refresher.Suspend();
    }

    // Returns the compositeTimeline of whatever users we are viewing
    // 
    public Timeline getCompositeTimeline() {
        return compositeTimeline;
    }

    // Sets the way the compositeTimeline should be organized by passing in
    // an enum OrganizeType
    //
    public void organize(OrganizeType type) {
        compositeTimeline.setOrganizeType(type);
        compositeTimeline.organize();
    }

    // Clears all the timelines and displayItems stored from the compositeTimeline
    //
    public void clearTimeline() {
        compositeTimeline.clearAll();
    }

    // Returns the current organize type in use
    // 
    public OrganizeType organizeType() {
        return compositeTimeline.getOrganizeType();
    }

    // Adds a subscriptionItem to the Timeline for viewing (search/tweeter)
    //
    public void addToTimeline(SubscriptionItem subscriptItemToAdd) {
        Timeline subscriptTimeline = subscriptItemToAdd.timeline();
        compositeTimeline.addTimeline(subscriptTimeline);
    }

    // Refreshes the entire compositeTimeline.  Every user/serach timeline is redownloaded
    // and the compositeTimeline is refreshed
    //
    public void refreshTimeline() {
        compositeTimeline.downloadAndParse();
    }

    // Adds an entire timeline to the CompositeTimeline
    //
    public void addTimeline(Timeline timeline) {
        compositeTimeline.addTimeline(timeline);
    }

    // Saves the timeline to disk
    //
    public void saveTimelines() {
        compositeTimeline.saveTimeline();
    }

    // Loads the previous timelines the user was viewing when the program last closed
    // 
    public void loadPreviousTimelines() {
        System.out.println("Loading Previous Timelines");
        File dir = new File("src");
        String[] files = dir.list();
        Document doc = null;
        Matcher matcher;
        Timeline timeline = null;
        SubscriptionItem sub = null;

        for (String file : files)
            if (file.contains("timeline") && file.contains(".xml")) {
                matcher = Pattern.compile("(?<=\\_).+?(?=\\.xml)").matcher(file);
                if (matcher.find()) {
                    System.out.println("Loading " + matcher.group(0));
                    doc = XMLHelper.getDocumentByLocation("src/" + file);
                    if (file.contains("user"))
                        // timeline = UserTimeline.parseFromDocument(matcher.group(0), doc);
                        sub = Tweeter.getTweeterFromDoc(doc);
                    else
                        timeline = SearchTimeline.parseFromDocument(matcher.group(0), doc);

                    if (timeline != null)
                        addTimeline(timeline);
                    if (sub != null)
                        addToTimeline(sub);
                }
            }
    }

    // Deletes the timelines, that were saved to file on system close, after they are initialized
    // into the program
    //
    public void deletePreviousTimelines() {
        File dir = new File("src");
        String[] files = dir.list();
        Document doc = null;

        for (String file : files)
            if (file.contains("timeline") && file.contains(".xml")) {
                File temp = new File("src/" + file);
                if (temp.delete())
                    System.out.println("File Deleted: " + file);
                else
                    System.out.println(file + " not delteed!");
            }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Threading
    //
    
    // Used to be a docking station for any other class who needs to notify this class that an event has finished
    // so the timelinesViewer can be refreshed upon updating.
    // 
    @Override
    public void stateReceived(ProgramStateEvent event) {
        if (event.state() == ProgramState.TIMELINE_MODIFIED || event.state() == ProgramState.TIMELINE_REFRESHED)
            timelinesVwr.refresh();
    }
}
