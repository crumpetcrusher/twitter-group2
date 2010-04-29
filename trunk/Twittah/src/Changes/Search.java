package Changes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import backend.XMLHelper;

import Timelines.SearchTimeline;
import Timelines.Timeline;

public class Search implements SubscriptionItem{

	private String _query;
	private ImageIcon image;
	private SearchTimeline timeline;
	
	public Search(String query)
	{
	    _query = query;
		try {
			image = new ImageIcon(new URL("http://bimmershopper.com/wp-content/uploads/2009/11/Search-icon-256.png"));
			Image test = image.getImage();
			test = test.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(test);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeline = SearchTimeline.parseFromDocument(XMLHelper.getTweetsByKeywords(query), query);
	}
	@Override
	public ImageIcon icon() {
		return image;
	}

	@Override
	public String text() {
		return _query;
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
