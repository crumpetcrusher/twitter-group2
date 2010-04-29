package Changes;

import Timelines.Timeline;

public interface SubscriptionItem {

	public String text();
	
	public javax.swing.ImageIcon icon();
	
	public boolean isSearch();
	
	public Timeline timeline();
	
}
