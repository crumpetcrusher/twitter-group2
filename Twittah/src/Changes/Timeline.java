package Changes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Timelines.UserTimeline;

public class Timeline {
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	private List<DisplayItem> displayItems = new ArrayList<DisplayItem>();
	
	private OrganizeType currentOrganize  = OrganizeType.JAN_DEC;
	
	
	public DisplayItem[] displayItems()
	{
		DisplayItem[] temp = new DisplayItem[displayItems.size()];
		
		
		return displayItems.toArray(temp);
	}
	
	
	public void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
		for(DisplayItem item : timeline.displayItems())
			System.out.println("Text: " + item.text());
		fill();
	}
	
	protected void addDisplayItem(DisplayItem newDisplayItem)
	{
		displayItems.add(newDisplayItem);
	}
	
	public void clear()
	{
		timelines.clear();
		displayItems.clear();
	}
	
	public void fill()
	{
		displayItems.clear();
		for(Timeline timeline : timelines)
		{
			//for(DisplayItem displayItem : ((UserTimeline)timeline).getUserTweets())
			for(DisplayItem displayItem : timeline.displayItems())
			{
				
				addDisplayItem(displayItem);
			}
		}
		organize();
	}
	
	public String printFeeds()
	{
		String temp = "";
		
		for(Timeline timeline : timelines)
			temp += timeline.toString() + "\r\n" ;
		
		return temp;
	}
	
	public String toString()
	{
		String value = "[Timeline]" + "\r\n";
		for(DisplayItem displayItem : displayItems)
			value += "\t" + displayItem.toString() + "\r\n";
		return value;
		
	}
	
	public void organize()
	{
		Collections.sort(displayItems, new DisplayItemOrganizer(currentOrganize));
	}


	public void removeTimeline(Timeline timeline) {
		//String toDeleteTimelineName = ((UserTimeline) timeline).getUserTweets().get(0).getTweeter().getUserName();
		
		for(Timeline temp : timelines) {
			if (temp.equals(timeline)){
				timelines.remove(timeline);
			}
		}
		
	}


	public void setOrganizeType(OrganizeType type) {
		currentOrganize = type;
		
	}

	
}
