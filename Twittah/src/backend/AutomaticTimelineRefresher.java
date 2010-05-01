//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Class Name           : AutomaticTimelineRefresher
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// 
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import Timelines.Timeline;

public class AutomaticTimelineRefresher extends Thread {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Class Attributes
    //
    
    // There are 4 attributes used in this class to manage automatic refreshing of a timeline:
    //
    // paused           :  (must be) Volatile boolean accessable from anywhere in Main memory used to let
    //                     any other threads know it's paused or not.
    //
    // _refreshRate     :  The time to count down from (in milliseconds).
    //
    // _timeline        :  Stores the timeline in which to refresh
    //
    // counter          :  This will count down from the _refreshRate, as per run()
    //
    
    private volatile boolean paused       = true;
    private int              _refreshRate = 30000;
    private Timeline         _timeline    = null;
    private int              counter;

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // Constructor that will take in a timeline and how often to refresh in seconds
    //
    public AutomaticTimelineRefresher(Timeline timeline, int timeInSecToRefresh) {
        if (timeline == null || timeInSecToRefresh < 1)
            stop();
        _timeline = timeline;
        _refreshRate = timeInSecToRefresh * 1000;
        start();
        Suspend();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // Override method, this code will run when start() is called
    //
    public void run() {
        while (isAlive()) {
            try {
                counter = _refreshRate / 1000;
                // Will count down to 0 from time requested
                while (counter > 0) {
                    if (counter % 5 == 0)
                        System.out.println("Refreshing in " + counter);
                    sleep(1000);
                    counter--;
                }
                // Downloads and parses the timeline
                _timeline.downloadAndParse();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // Suspends the running of this thread
    //
    public void Suspend() {
        paused = true;
        counter = _refreshRate / 1000;
        super.suspend();
    }

    // Resumes the running of this thread
    //
    public void Resume() {
        paused = false;
        super.resume();
    }

    // Lets outsiders know if it is paused
    //
    public boolean isPaused() {
        return paused;
    }
}
