package backend;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Changes.OrganizeType;
import Changes.Timeline;
import Timelines.CompositeTimeline;
import Timelines.SearchTimeline;
import Timelines.UserTimeline;
import Twitter.Tweeter;

public class TimelinesManager {
	
	private SubscriptionsManager subscriptionsMgr;
	//private Timeline compositeTimeline = new Timeline();
	private CompositeTimeline compositeTimeline = new CompositeTimeline();

	public TimelinesManager(SubscriptionsManager newSubscriptionsMgr) {

		subscriptionsMgr = newSubscriptionsMgr;
		load();
		//initialize();
		
	}
	
	private void load()
	{
		loadPreviousTimelines();
	}
	
	protected void initialize() {
		
		for (Tweeter tweeter : subscriptionsMgr.getSubscriptions())
		{
			compositeTimeline.addTimeline(tweeter.getUserTimeline());
		}
		compositeTimeline.refresh();
		
	}
	
	public void setSubscriptionsManager(SubscriptionsManager newSubscriptionsMgr) {
		subscriptionsMgr = newSubscriptionsMgr;
	}
	
	
	
	public Timeline getCompositeTimeline() {
		return compositeTimeline;
	}
	
	public void setOrganizeType(OrganizeType type) {
		compositeTimeline.setOrganizeType(type);
		
	}
	
	public void clearTimeline() {

		compositeTimeline.clearAll();

	}
	
	public void addUserToTimeline(String name) {

		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()){
			if (tweeter.getUserName().equals(name)) {
				compositeTimeline.addTimeline(tweeter.getUserTimeline());
			}
		}
		//compositeTimeline.fill();
		//compositeTimeline.organize();
		
	}

	public void removeUserFromTimeline(String name) {

		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()){
			if (tweeter.getUserName().equals(name)) {
				compositeTimeline.removeTimeline(tweeter.getUserTimeline());
			}
		}
		//compositeTimeline.fill();
		//compositeTimeline.organize();

	}

	
	public void refreshTimeline() {
		compositeTimeline.downloadAndParse();
	}
	
	
	public void addSearchToTimeline(String query) {
		SearchTimeline searchTimeline = new SearchTimeline(query);
		compositeTimeline.addTimeline(searchTimeline);
		//compositeTimeline.fill();
		//compositeTimeline.organize();
	}
	
	public void addTimeline(Timeline timeline)
	{
		compositeTimeline.addTimeline(timeline);
	}
	
	public void saveTimelines()
	{
		compositeTimeline.saveTimeline();
	}
	
	public void loadPreviousTimelines()
	{
		File dir = new File("src");
		String[] files = dir.list();
		Document doc = null;
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
				System.out.println("Loading " + file);
				doc = XMLHelper.getDocumentByLocation("src/" + file);
				if(file.contains("user"))
				{
					addTimeline(UserTimeline.parseFromDocument(doc));
					System.out.println(UserTimeline.parseFromDocument(doc));
				}
				else
					addTimeline(SearchTimeline.parseFromDocument(doc));
			}
	}
	
	public void deletePreviousTimelines()
	{
		File dir = new File("src");
		String[] files = dir.list();
		Document doc = null;
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
				File temp = new File("src/" + file);
				if(temp.delete())
					System.out.println("File Deleted: " + file);
				else
					System.out.println(file + " not delteed!");
			}
	}

	
}
