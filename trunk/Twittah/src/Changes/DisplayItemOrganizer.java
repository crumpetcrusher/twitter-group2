//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project      : IST240 - Twitter Application
//
// Class Name   : DisplayItemOrganizer
//    
// Authors      : Scott Smiesko, Rick Humes
// Date         : 2010-30-04
//
//
// DESCRIPTION
// This class is used to organize a list of DisplayItem.
//
// Use:  Collections.sort(displayItemList, new DisplayItemOrganizer(OrganizeType.A_Z));
//       This will sort the list A to Z. Check out the ENUM OrganizeType for more.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package Changes;

import java.util.Comparator;
import java.util.Date;

public class DisplayItemOrganizer implements Comparator<DisplayItem>{
    
    // This class has 1 component. Stored for information on how to organize the list.
    //
    // type             : The type of organization used for a list.
	
    private OrganizeType type;
    
    //The constructor method, which will take a OrganizeType in.
    public DisplayItemOrganizer(OrganizeType typeIN)
    {
        type = typeIN;
    }
    
    // This method must be overridden due to Comparator implementation. This method actually does the comparing,
    // returning if the one is greater, equal, or less than the other.
    @Override
    public int compare(DisplayItem item1, DisplayItem item2) {
        int result = 0;
        
        String  comparee1Text = item1.text().toLowerCase(), comparee2Text = item2.text().toLowerCase();
        Date comparee1Date = item1.date(), comparee2Date = item2.date();
        
        if(type == OrganizeType.A_Z || type ==  OrganizeType.Z_A)
        {
            result = comparee1Text.compareTo(comparee2Text);
            if(type == OrganizeType.Z_A)
                result *= -1;
        }
        else
        {
            result = comparee1Date.compareTo(comparee2Date);
            if(type == OrganizeType.JAN_DEC)
                result *= -1;
        }
        
        return result;
    }

}
