//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Interface Name   : SubscriptionItem
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This interface is the template for being able to be displayed on our subscription list.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Changes;

import Timelines.Timeline;

public interface SubscriptionItem {

    // This class has 3 components used to store information about the search term.
    //
    // text()           : The identifier, usually the search term or a tweeters name.
    //
    // icon()           : The icon of the tweeter, or a magnifying glass for a search. (as of now)
    //
    // isSearch()       : Whether or not this item is a search. Search - True, Tweeter - False
    //
    // timeline()       : The timeline associated with this item.
    //
    public String text();
    
    public javax.swing.ImageIcon icon();
    
    public boolean isSearch();
    
    public Timeline timeline();
	
}
