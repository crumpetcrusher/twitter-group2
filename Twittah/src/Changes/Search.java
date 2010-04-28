package Changes;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import backend.XMLHelper;

import Timelines.SearchTimeline;

public class Search implements SubscriptionItem{

	private String query;
	private ImageIcon image;
	private SearchTimeline timeline;
	
	public Search(String query)
	{
		try {
			image = new ImageIcon(new URL("http://s.twimg.com/a/1271891196/images/default_profile_5_normal.png"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeline = SearchTimeline.parseFromDocument(XMLHelper.getTweetsByKeywords(query));
	}
	@Override
	public ImageIcon icon() {
		return image;
	}

	@Override
	public String text() {
		return query;
	}

	@Override
	public boolean isSearch() {
		return true;
	}
	@Override
	public Timeline timeline() {
		return timeline;
	}

}
