//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Class Name           : Search
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This class is used to be able to display a search on our subscriptions list. Hence, implements SubscriptionItem.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Changes;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import backend.XMLHelper;
import Timelines.SearchTimeline;
import Timelines.Timeline;

public class Search implements SubscriptionItem{
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class has 3 attributes used to store information about the search term.
    //
    // _query           : The query that was submitted.
    //
    // image            : The image that is associated with this, which will ALWAYS be a Magnifying Glass.
    //
    // timeline         : The timeline that is associated with this SubscriptionItem
    //
    //
    private String _query;
    private ImageIcon image;
    private SearchTimeline timeline;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // The class constructor that will take in a query and automatically go to work downloading and parsing
    // the timeline.
    //
    public Search(String query)
    {
        _query = query;
        try 
        {
            image = new ImageIcon(new URL("http://bimmershopper.com/wp-content/uploads/2009/11/Search-icon-256.png"));
            Image test = image.getImage();
            test = test.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(test);
        } 
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        }
        
        // Does the actual downloading and parsing.
        //
        timeline = SearchTimeline.parseFromDocument(XMLHelper.getTweetsByKeywords(query), query);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // This method will return the items icon.
    //
    @Override
    public ImageIcon icon() {
            return image;
    }

    // This method will return the items identifier, in this case the query.
    //
    @Override
    public String text() {
            return _query;
    }

    // This method will return whether or not this item is a search, which it is! So, true always.
    //
    @Override
    public boolean isSearch() {
            return true;
    }
    
    // This method will return the timeline associated with this item.
    //
    @Override
    public Timeline timeline() {
            return timeline;
    }

}
