package RandomClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public interface Feed {
	
	public FeedItem[] feedItems();
	
	public void refresh();
	
	public void organizeByDate();
	
	public void organizeByText();
	
	public void organizeBySource();
	
	public void add(Feed feed);

	public String toString();
}
