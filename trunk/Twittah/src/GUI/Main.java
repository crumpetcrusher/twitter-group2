package GUI;

import java.awt.BorderLayout;
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

import Twitter.SubscriptionsManager;

public class Main extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5964038338276344070L;

	private SubscriptionsManager subscriptionsMgr;
	
	private SubscriptionsViewer	 subscriptionsVwr;
	private TimelineViewer		 timelineVwr;
	private ButtonManager		 buttonMgr;
	
	private JPanel				 buttonPanel;
	private JButton				 addSubscriptionButton;
	private JButton				 deleteSubscriptionButton;
	private JButton				 refreshTimelineButton;
	private JButton				searchButton;
	
	
	/**
	 * GUI creation
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	public Main() throws Exception
	{

		setLayout(new BorderLayout());
		
		subscriptionsMgr = new SubscriptionsManager("src/subscriptionlist.xml");
		buttonMgr 		 = new ButtonManager();
		subscriptionsVwr = new SubscriptionsViewer();
		timelineVwr		 = new TimelineViewer();
		
		
		
		buttonMgr.setSubscriptionsManager(subscriptionsMgr);
		buttonMgr.setSubscriptionsViewer(subscriptionsVwr);
		buttonMgr.setTimelineViewer(timelineVwr);
		
		
		
		subscriptionsVwr.setSubscriptionsManager(subscriptionsMgr);
		subscriptionsVwr.setButtonManager(buttonMgr);
		subscriptionsVwr.refreshSubscriptionsViewer();
		
		
		add(subscriptionsVwr, BorderLayout.WEST);
		
		timelineVwr.setSubscriptionsManager(subscriptionsMgr);
		timelineVwr.refreshTimelineViewer();
		
		
		add(timelineVwr, BorderLayout.EAST);
		
		
		
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
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		searchButton = new JButton("Search");
		
		searchButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = JOptionPane.showInputDialog("Query: ");
						if ((name == null) || (name == "")) {
							System.out.println("No text to search for");
						}
						else{
							buttonMgr.search(name);
						}
					}
				});

		buttonPanel.add(searchButton);
		
		
		
		
		
        
	}
	
	public static void main(String[] args) throws Exception
	{		
		
		final Main main = new Main();
		
		JFrame frame = new JFrame("Twittah!");
		final Thread 	refreshThread = ( new Thread() {

			public void run() {
				
				main.buttonMgr.doRefreshTimeline();

			}

			}

			);
		
		// Menubar
		//
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu sort = new JMenu("Sort");
		JMenu options = new JMenu("Options");
		
		ButtonGroup group = new ButtonGroup();
		
		JCheckBoxMenuItem refreshAuto = new JCheckBoxMenuItem("Refresh Automatically");//new JRadioButtonMenuItem("Refresh Automatically");
		refreshAuto.setSelected(true);
		refreshAuto.setMnemonic(KeyEvent.VK_R);
		group.add(refreshAuto);
		options.add(refreshAuto);
		
		
		refreshAuto.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						JCheckBoxMenuItem item = ((JCheckBoxMenuItem) e.getSource());
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							System.out.println("TRUE");
							refreshThread.stop();
						}
						else
						{
							System.out.println("FALSE");
							refreshThread.start();
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
		frame.setSize(600,600);
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(main);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
}
