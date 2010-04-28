package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Timelines.SearchTimeline;
import Timelines.UserTimeline;
import backend.ButtonManager;
import backend.SubscriptionsManager;
import backend.TimelinesManager;
import backend.XMLHelper;

public class RootGUI extends JPanel{
	

    
    private SubscriptionsViewer      subscriptionsVwr;
    private TimelinesViewer          timelinesVwr;
    
    private JPanel                   buttonPanel;
    private JButton                  addSubscriptionButton;
    private JButton                  deleteSubscriptionButton;
    private JButton                  refreshTimelineButton;
    private JButton                  searchButton;
    private JButton					 testButton;
    private SubscriptionsManager 	 subscriptionsMgr;
    private TimelinesManager         timelinesMgr;
    private ButtonManager            buttonMgr;
    JCheckBoxMenuItem refreshAuto;
	
	public RootGUI() {
		
	    subscriptionsMgr = new SubscriptionsManager("src/subscriptionlist.xml");
	    timelinesMgr     = new TimelinesManager(subscriptionsMgr);
	    buttonMgr        = new ButtonManager();
	    subscriptionsVwr = new SubscriptionsViewer();
	    timelinesVwr     = new TimelinesViewer();
	    
	    
	    
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
	    
	    //Test BUTTON
	    testButton = new JButton("Test");
	    
	    testButton.addActionListener(
	                    new ActionListener() {
	                            public void actionPerformed(ActionEvent e) {
	                                    //String name = JOptionPane.showInputDialog("Query: ");
	                            	/*
	                        		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	                        		DocumentBuilder docBuilder;
									try {
										
										docBuilder = dbfac.newDocumentBuilder();
	                            		Document document = docBuilder.newDocument();
	                            		Element root = document.createElement("Settings");
	                                	//subscriptionsMgr.writeData(root, document);
	                                	writeSettings(root, document);
	                            		document.appendChild(root);
	                                	XMLHelper.writeDocument(document, "src/test.xml");
	                                	timelinesMgr.saveTimelines();
									} catch (ParserConfigurationException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}*/
		                            loadPreviousTimelines();
	                            }

	                    });
	
	    buttonPanel.add(testButton);
	    
	    
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
	    
        final Thread refreshThread = (new Thread() {
            public void run() {
                    do {
                            try {
                                    //sleep(10000);		// this does 10 seconds
                                    sleep(60000);		// this does 60 seconds
                                    buttonMgr.doRefreshTimeline();
                            } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            }
                            
                    } while (isAlive());
            }
    });
    refreshThread.start();
    refreshThread.suspend();
    
    JFrame frame = new JFrame("Twittah!");

    // Menubar
    //
    JMenuBar menubar        = new JMenuBar();
    JMenu file              = new JMenu("File");
    JMenu sort              = new JMenu("Sort");
    JMenu options           = new JMenu("Options");
    ButtonGroup sortGroup   = new ButtonGroup();
    
    
    JRadioButtonMenuItem sortByDate = new JRadioButtonMenuItem("Date");
    sortByDate.setSelected(true);
    sortByDate.setMnemonic(KeyEvent.VK_R);
    sortGroup.add(sortByDate);
    sort.add(sortByDate);
    
    sortByDate.addActionListener(
                    new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                    buttonMgr.sortByDate();
                            }
                    });

    JRadioButtonMenuItem sortByAscend = new JRadioButtonMenuItem("Alphabetical Ascending");
    sortByAscend.setMnemonic(KeyEvent.VK_O);
    sortGroup.add(sortByAscend);
    sort.add(sortByAscend);
    
    sortByAscend.addActionListener(
                    new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                     buttonMgr.sortByAscend();
                            }
                    });

    JRadioButtonMenuItem sortByDescend = new JRadioButtonMenuItem("Alphabetical Descending");
    sortByAscend.setMnemonic(KeyEvent.VK_O);
    sortGroup.add(sortByDescend);
    sort.add(sortByDescend);
    
    sortByDescend.addActionListener(
                    new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                     buttonMgr.sortByDescend();
                            }
                    });
    
    refreshAuto = new JCheckBoxMenuItem("Refresh Automatically");
    refreshAuto.setSelected(false);
    refreshAuto.setMnemonic(KeyEvent.VK_R);
    options.add(refreshAuto);
    
    
    refreshAuto.addItemListener(
                    new ItemListener() {
                            public void itemStateChanged(ItemEvent e) {
                                    JCheckBoxMenuItem item = ((JCheckBoxMenuItem) e.getSource());
                                    if(e.getStateChange() == ItemEvent.SELECTED)
                                    {
                                            System.out.println("Start");
                                            SwingUtilities.invokeLater(new Runnable() {
                                                public void run() {
                                                    refreshThread.resume();
                                                }
                                            });
                                    }
                                    if(e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                            System.out.println("Stop");
                                            SwingUtilities.invokeLater(new Runnable() {
                                                public void run() {
                                                    refreshThread.suspend();
                                                }
                                            });
                                    }
                            }
                    });
    
    menubar.add(file);
    menubar.add(sort);
    menubar.add(options);

    // TwitterApp Properties
    //
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setJMenuBar(menubar);
    frame.getContentPane().add(this);
    frame.setPreferredSize(new Dimension(700, 500));
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosing(WindowEvent winEvt) {
            //String name = JOptionPane.showInputDialog("Query: ");
        	
        	timelinesMgr.deletePreviousTimelines();
			writeSettings();
			timelinesMgr.saveTimelines();
            System.exit(0); 
        }
    });
    
	}
	
	public void writeSettings()
	{
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			
			docBuilder = dbfac.newDocumentBuilder();
    		Document document = docBuilder.newDocument();
    		Element root = document.createElement("Settings");
        	//subscriptionsMgr.writeData(root, document);
    		//root.appendChild(subscriptionsMgr.getData(doc));
    		document.appendChild(root);
        	XMLHelper.writeDocument(document, "src/test.xml");
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void loadPreviousTimelines()
	{
		File dir = new File("src");
		String[] files = dir.list();
		Document doc = null;
		
		for(String file : files)
			if(file.contains("timeline") && file.contains(".xml"))
			{
				doc = XMLHelper.getDocumentByLocation("src/" + file);
				if(file.contains("user"))
					timelinesMgr.addTimeline(UserTimeline.parseFromDocument(doc));
				else
					timelinesMgr.addTimeline(SearchTimeline.parseFromDocument(doc));
			}
	}
}
