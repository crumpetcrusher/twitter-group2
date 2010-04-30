package GUI;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Changes.DisplayItem;
import backend.TimelinesManager;

public class TimelinesViewer extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6080155935322827731L;
	private TimelinesManager timelinesMgr;
	private Container  timelineItemsPanel;
	private JScrollPane timelineScrollPane;
	
	public TimelinesViewer(TimelinesManager newTimelinesMgr) {
		
		timelinesMgr = newTimelinesMgr;
		setLayout(new BorderLayout());
		timelineScrollPane = new JScrollPane();
		
		timelineItemsPanel = Box.createVerticalBox();
		
		timelineScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		timelineScrollPane.setViewportView(timelineItemsPanel);
		
		add(timelineScrollPane, BorderLayout.CENTER);
		
	    refresh();
	}
	
	
	public void refresh() {
		
		timelineItemsPanel.removeAll();
	
		for(DisplayItem tweet : timelinesMgr.getCompositeTimeline().displayItems()) {
		    //System.out.println(tweet.)
			DisplayItemViewer displayItem = new DisplayItemViewer(tweet);
			timelineItemsPanel.add(Box.createVerticalGlue());
			timelineItemsPanel.add(displayItem);
			timelineItemsPanel.add(Box.createVerticalStrut(10));
		}
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	setVisible(false);
		    	setVisible(true);
		    }
		});
		
	}

}