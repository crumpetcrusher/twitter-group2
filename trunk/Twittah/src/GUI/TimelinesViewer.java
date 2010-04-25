package GUI;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Changes.DisplayItem;
import backend.TimelinesManager;

public class TimelinesViewer extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6080155935322827731L;
	private TimelinesManager timelinesMgr;
	private JPanel timelineItemsPanel;
	private JScrollPane timelineScrollPane;
	
	public TimelinesViewer() {
		
		timelineItemsPanel = new JPanel();
		timelineScrollPane = new JScrollPane();
		
		timelineItemsPanel.setLayout(new GridLayout(0, 1));
		
		timelineScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		timelineScrollPane.setPreferredSize(new Dimension(500, 500));
		timelineScrollPane.setViewportView(timelineItemsPanel);
		
		add(timelineScrollPane);
		
	}
	
	public void setTimelinesManager(TimelinesManager newTimelinesMgr) {
		timelinesMgr = newTimelinesMgr;
	}
	
	
	public void refreshTimelinesViewer() {
		
		timelineItemsPanel.removeAll();
	
		for(DisplayItem tweet : timelinesMgr.getCompositeTimeline().displayItems()) {
			DisplayItemViewer displayItem = new DisplayItemViewer(tweet);
			timelineItemsPanel.add(displayItem);
		}

		
	}
	
	public void repaintTimelinesViewer() {
		timelineItemsPanel.repaint();
		this.repaint();
	}

}