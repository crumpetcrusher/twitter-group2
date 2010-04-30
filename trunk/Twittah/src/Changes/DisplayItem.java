//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Interface Name       : DisplayItem
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This class is a template for any item that wishes to be displayed in a timeline. Must return all of the following
// method with proper information.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Changes;

public interface DisplayItem {
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Interface
    //
    
    // This class has 5 interface methods used to relay information about a item to be displayer in a timeline:
    //
    // owner()          : The owner of this display item, usually a tweeter.
    //
    // source()         : The medium used to make this display item (most of the time web, twitteroid, Seesmic)
    //
    // text()           : The body, if you will, of the item.
    //
    // date()           : The date this item was created.
    //
    // icon()           : The icon associated with this item, usually a tweeters user picture.
    //
    //
    public String owner();
		
    public String source();
    
    public String text();
		
    public java.util.Date date();
    
    public javax.swing.ImageIcon icon();
    
}
