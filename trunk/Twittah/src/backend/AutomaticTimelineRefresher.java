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
// HEY PUT A GODDAMN DESCRIPTION IN HERE!!!!!!!!!!!!!!!!!!!!!!
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import Timelines.Timeline;


public class AutomaticTimelineRefresher extends Thread {
    // Must be volatile:
    private volatile boolean paused = true;
    private int _refreshRate = 30000;
    private ButtonManager _buttonMgr;
    private Timeline _timeline = null;
    private int counter;
    
    public AutomaticTimelineRefresher(Timeline timeline, int timeInSecToRefresh)
    {
        if(timeline == null || timeInSecToRefresh < 1)
            stop();
        _timeline = timeline;
        _refreshRate = timeInSecToRefresh * 1000;
        start();
        Suspend();
    }

    public void run() {
      while (isAlive())
      {
          try 
          {
              counter = _refreshRate / 1000;
              while(counter > 0)
              {
                  if(counter % 5 == 0)
                      System.out.println("Refreshing in " + counter);
                  sleep(1000);
                  counter--;
              }
              _timeline.downloadAndParse();
          }
          catch (InterruptedException e) 
          {
            e.printStackTrace();
          }
          
      }
    }

    public void Suspend() 
    {
        paused = true;
        counter = _refreshRate / 1000;
        super.suspend();
    }
    
    public void Resume()
    {
        paused = false;
        super.resume();
    }
    
    public boolean isPaused()
    {  
      return paused;
    }
}
