package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import backend.ButtonManager;
import backend.SubscriptionsManager;
import backend.TimelinesManager;

public class Main extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5964038338276344070L;

	private SubscriptionsManager subscriptionsMgr;
	private TimelinesManager	 timelinesMgr;
	private ButtonManager		 buttonMgr;
	
	private SubscriptionsViewer	 subscriptionsVwr;
	private TimelinesViewer		 timelinesVwr;
	
	private JPanel				 buttonPanel;
	private JButton				 addSubscriptionButton;
	private JButton				 deleteSubscriptionButton;
	private JButton				 refreshTimelineButton;
	private JButton				 searchButton;
	
	/**
	 * GUI creation
	 * @param container 
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	public Main() throws Exception
	{
		subscriptionsMgr = new SubscriptionsManager("src/subscriptionlist.xml");
		timelinesMgr	 = new TimelinesManager(subscriptionsMgr);
		buttonMgr 		 = new ButtonManager();
		
		subscriptionsVwr = new SubscriptionsViewer();
		timelinesVwr	 = new TimelinesViewer();
		
		
		
		buttonMgr.setSubscriptionsManager(subscriptionsMgr);
		buttonMgr.setSubscriptionsViewer(subscriptionsVwr);
		buttonMgr.setTimelinesManager(timelinesMgr);
		buttonMgr.setTimelinesViewer(timelinesVwr);
		
		timelinesVwr.setTimelinesManager(timelinesMgr);
		
		subscriptionsVwr.setSubscriptionsManager(subscriptionsMgr);
		subscriptionsVwr.setButtonManager(buttonMgr);

		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		
		
		setLayout(new BorderLayout());
		
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(1,0));
		
		addSubscriptionButton = new JButton("Add Subscription");
		
		addSubscriptionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter Username:");
						if ((name == null) || (name == "")) {
							System.out.println("no user entered");
						}
						else{
							buttonMgr.doAddSubscription(name);
							
						}
					}
				});
		
		buttonPanel.add(addSubscriptionButton);
		
		deleteSubscriptionButton = new JButton("Delete Subscription");
		
		deleteSubscriptionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Enter username:");
						if ((name == null) || (name == "")) {
							System.out.println("no user entered");
						}
						else{
							buttonMgr.doDeleteSubscription(name);
						}
					}
				});

		buttonPanel.add(deleteSubscriptionButton);
		
		refreshTimelineButton = new JButton("Refresh Timeline");
		
		refreshTimelineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							buttonMgr.doRefreshTimeline();
					}
				});
		
		buttonPanel.add(refreshTimelineButton);
		
		searchButton = new JButton("Search");
		
		searchButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Query: ");
						if ((name == null) || (name == "")) {
							System.out.println("No text to search for");
						}
						else{
							buttonMgr.doSearch(name);
						}
					}
				});

		buttonPanel.add(searchButton);
		
		add(subscriptionsVwr, BorderLayout.WEST);
		add(timelinesVwr, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
        
	}
	
	
	public static void main(String[] args) throws Exception {		
		final Main main = new Main();
		final Thread refreshThread = (new Thread() {
			public void run() {
				do {
					try {
						sleep(10000);
						main.buttonMgr.doRefreshTimeline();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} while (isAlive());
			}
		});
		
		JFrame frame = new JFrame("Twittah!");

		// Menubar
		//
		JMenuBar menubar 	= new JMenuBar();
		JMenu file 		 	= new JMenu("File");
		JMenu sort 		 	= new JMenu("Sort");
		JMenu options		= new JMenu("Options");
		ButtonGroup group 	= new ButtonGroup();
		
		
		JCheckBoxMenuItem refreshAuto = new JCheckBoxMenuItem("Refresh Automatically");
		refreshAuto.setSelected(true);
		refreshAuto.setMnemonic(KeyEvent.VK_R);
		options.add(refreshAuto);
		
		
		refreshAuto.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						JCheckBoxMenuItem item = ((JCheckBoxMenuItem) e.getSource());
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							System.out.println("TRUE");
							SwingUtilities.invokeLater(new Runnable() {
							    public void run() {
							    	refreshThread.start();
							    }
							});
						}
						if(e.getStateChange() == ItemEvent.DESELECTED)
						{
							System.out.println("FALSE");
							SwingUtilities.invokeLater(new Runnable() {
							    public void run() {
							    	refreshThread.interrupt();
							    }
							});
						}
					}
				});
		
		JRadioButtonMenuItem sortByDate = new JRadioButtonMenuItem("Date");
		sortByDate.setSelected(true);
		sortByDate.setMnemonic(KeyEvent.VK_R);
		group.add(sortByDate);
		sort.add(sortByDate);
		
		sortByDate.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main.buttonMgr.sortByDate();
					}
				});

		JRadioButtonMenuItem sortByAscend = new JRadioButtonMenuItem("Alphabetical Ascending");
		sortByAscend.setMnemonic(KeyEvent.VK_O);
		group.add(sortByAscend);
		sort.add(sortByAscend);
		
		sortByAscend.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main.buttonMgr.sortByAscend();
					}
				});

		JRadioButtonMenuItem sortByDescend = new JRadioButtonMenuItem("Alphabetical Descending");
		sortByAscend.setMnemonic(KeyEvent.VK_O);
		group.add(sortByDescend);
		sort.add(sortByDescend);
		
		sortByDescend.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main.buttonMgr.sortByDescend();
					}
				});
		
		menubar.add(file);
		menubar.add(sort);
		menubar.add(options);
    
		// TwitterApp Properties
		//
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(main);
		frame.setPreferredSize(new Dimension(700, 500));
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
}
