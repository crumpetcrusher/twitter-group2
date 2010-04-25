package GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JButton				searchButton;
	
	GridBagConstraints constraints = new GridBagConstraints();
	
	/**
	 * GUI creation
	 * @param container 
	 * 
	 * @param subscribedTweeters - list of tweeter objects to use throughout the GUI
	 * @return GUI - one mass container of all objcts created inside this function
	 * @throws Exception 
	 */
	public Main(Container rootpane) throws Exception
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
		buttonMgr.setRootPane(rootpane);
		
		timelinesVwr.setTimelinesManager(timelinesMgr);
		
		subscriptionsVwr.setSubscriptionsManager(subscriptionsMgr);
		subscriptionsVwr.setButtonManager(buttonMgr);

		subscriptionsVwr.refreshSubscriptionsViewer();
		timelinesVwr.refreshTimelinesViewer();
		
		
		setLayout(new GridBagLayout());
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		
		int x, y;
		
		constraints.gridheight = 2;
		
		addGB(subscriptionsVwr, x = 0, y = 0);
		addGB(timelinesVwr, x = 1, y = 0);
		
		
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
		
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		
		addGB(buttonPanel, x = 0, y = 3);
		
		
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
	
	protected void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		add(component, constraints);
	}
	
	public static void main(String[] args) throws Exception
	{		
		
		
		JFrame frame = new JFrame("Twittah!");
		final Main main = new Main(frame.getContentPane());
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
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(main);
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
}
