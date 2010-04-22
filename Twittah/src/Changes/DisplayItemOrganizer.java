package Changes;

import java.util.Comparator;
import java.util.Date;

public class DisplayItemOrganizer implements Comparator<DisplayItem>{
	
	private OrganizeType type;
	
	public DisplayItemOrganizer(OrganizeType typeIN)
	{
		type = typeIN;
	}
	
	@Override
	public int compare(DisplayItem item1, DisplayItem item2) {
		int result = 0;
		
		String  comparee1Text = item1.text(), comparee2Text = item2.text();
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
			if(type == OrganizeType.DEC_JAN)
				result *= -1;
		}
		
		return result;
	}

}
