package backend;

import Changes.OrganizeType;
import Changes.Timeline;
import Timelines.SearchTimeline;
import Twitter.Tweeter;

public class TimelinesManager {
	
	private SubscriptionsManager subscriptionsMgr;
	private Timeline compositeTimeline = new Timeline();

	public TimelinesManager(SubscriptionsManager newSubscriptionsMgr) {

		subscriptionsMgr = newSubscriptionsMgr;
		initialize();
		
	}
	
	protected void initialize() {
		
		for (Tweeter tweeter : subscriptionsMgr.getSubscriptions())
		{
			compositeTimeline.addTimeline(tweeter.getUserTimeline());
		}
		compositeTimeline.fill();
		compositeTimeline.organize();
		
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

		compositeTimeline.clear();

	}
	
	public void addUserToTimeline(String name) {

		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()){
			if (tweeter.getUserName().equals(name)) {
				compositeTimeline.addTimeline(tweeter.getUserTimeline());
			}
		}
		compositeTimeline.fill();
		compositeTimeline.organize();
		
	}

	public void removeUserFromTimeline(String name) {

		for(Tweeter tweeter : subscriptionsMgr.getSubscriptions()){
			if (tweeter.getUserName().equals(name)) {
				compositeTimeline.removeTimeline(tweeter.getUserTimeline());
			}
		}
		compositeTimeline.fill();
		compositeTimeline.organize();

	}

	
	public void refreshTimeline() {
				compositeTimeline.fill();
				compositeTimeline.organize();
	}
	
	
	public void addSearchToTimeline(String query) {
		SearchTimeline searchTimeline = new SearchTimeline(query);
		compositeTimeline.addTimeline(searchTimeline);
		compositeTimeline.fill();
		compositeTimeline.organize();
	}

	
}
