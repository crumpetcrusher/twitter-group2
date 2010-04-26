package Timelines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Changes.DisplayItem;
import Changes.DisplayItemOrganizer;
import Changes.OrganizeType;
import Changes.Timeline;
import Twitter.Tweet;

public class CompositeTimeline extends Timeline{
	
	private List<Timeline> timelines = new ArrayList<Timeline>();
	
	public void addTimeline(Timeline timeline)
	{
		timelines.add(timeline);
		fill();
	}
	
	public void clearAll()
	{
		clearItems();
		timelines.clear();
	}
	
	public void refresh() {

		
	}
	
	public void fill()
	{
		clearItems();
		for(Timeline timeline : timelines)
			for(DisplayItem displayItem : timeline.displayItems())
				addDisplayItem(displayItem);
		organize();
	}

	public void removeTimeline(Timeline timeline) {

		for(Timeline temp : timelines) {
			if (temp.equals(timeline)){
				timelines.remove(timeline);
			}
		}
		fill();
		
	}
	
	protected void reload() {}

	@Override
	public void downloadAndParse() {
		for(Timeline timeline :  timelines)
		{
			timeline.clearItems();
			timeline.downloadAndParse();
		}
		fill();
	}
/*
	@Override
	public Element toElement(Document doc) {
		Element temp = doc.createElement("Timeline");
		temp.setAttribute("Type", "Composite");
		for(Timeline timeline : timelines)
			temp.appendChild(timeline.toElement(doc));
		return temp;
	}*/

	@Override
	public void saveTimeline() {
		for(Timeline timeline : timelines)
			timeline.saveTimeline();
	}

	
}
