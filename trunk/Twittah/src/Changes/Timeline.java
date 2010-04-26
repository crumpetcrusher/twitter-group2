package Changes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Timelines.CompositeTimeline;

public abstract class Timeline {
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	protected List<DisplayItem> displayItems = new ArrayList<DisplayItem>();
	
	private OrganizeType currentOrganize  = OrganizeType.JAN_DEC;
	
	protected Document timelineXML = null;
	
	
	public DisplayItem[] displayItems()
	{
		DisplayItem[] temp = new DisplayItem[displayItems.size()];
		
		
		return displayItems.toArray(temp);
	}
	
	/*
	public void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
		for(DisplayItem item : timeline.displayItems())
			System.out.println("Text: " + item.text());
		fill();
	}*/
	
	protected void addDisplayItem(DisplayItem newDisplayItem)
	{
		displayItems.add(newDisplayItem);
	}
	/*
	public void clearAll()
	{
		clearItems();
		timelines.clear();
	}*/
	
	public void clearItems()
	{
		displayItems.clear();
	}
	
	/*
	public void refresh() {
		for(Timeline timeline :  timelines)
		{
			timeline.clearItems();
			timeline.downloadAndParse();
		}
		clearItems();
		fill();
		
	}
	
	public void fill()
	{
		for(Timeline timeline : timelines)
		{
			//for(DisplayItem displayItem : ((UserTimeline)timeline).getUserTweets())
			for(DisplayItem displayItem : timeline.displayItems())
			{
				
				addDisplayItem(displayItem);
			}
		}
		organize();
	}*/
	
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

/*
	public void removeTimeline(Timeline timeline) {

		for(Timeline temp : timelines) {
			if (temp.equals(timeline)){
				timelines.remove(timeline);
			}
		}
		fill();
		
	}
*/

	public void setOrganizeType(OrganizeType type) {
		currentOrganize = type;
		
	}


	public abstract void downloadAndParse();
	
	public abstract void saveTimeline();
	
}
	


	
