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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Changes.*;

import Timelines.SearchTimeline;
import Timelines.UserTimeline;
import backend.ButtonManager;
import backend.SubscriptionsManager;
import backend.TimelinesManager;
import backend.XMLHelper;

public class RootGUI extends JPanel{
    private JPanel                   buttonPanel;
    private JButton                  addSubscriptionButton;
    private JButton                  deleteSubscriptionButton;
    private JButton                  refreshTimelineButton;
    private JButton                  searchButton;
    private JButton					 testButton;
    private ButtonManager            buttonMgr;
    private int refreshTime = 30000;
    JCheckBoxMenuItem refreshAuto;
	
	public RootGUI() {
		
	    buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(1,0));
	    setLayout(new BorderLayout());
	    
	    buttonMgr        = new ButtonManager(this);
	   // buttonMgr.loadPreviousTimelines();
	    

	    addSubscriptionButton = new JButton("Add Subscription");
	    
	    addSubscriptionButton.addActionListener(
	                    new ActionListener() {
	                            public void actionPerformed(ActionEvent e) {
	                                    String name = JOptionPane.showInputDialog("Enter Username:");
	                                    if ((name == null) || (name == "")) {
	                                            System.out.println("no user entered");
	                                    }
	                                    else{
	                                            buttonMgr.doAddSubscriptionTweeter(name);
	                                            
	                                    }
	                            }
	                    });
	    
	    buttonPanel.add(addSubscriptionButton);
	    
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
	    
	    add(buttonPanel, BorderLayout.SOUTH);
	    
        final Thread refreshThread = (new Thread() {

            public void run() {
                    do {
                            try {
                                    //sleep(10000);		// this does 10 seconds
                                    sleep(refreshTime);		// this does 60 seconds
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
    frame.setResizable(false);

    // Menubar
    //
    JMenuBar menubar        = new JMenuBar();
    JMenu view              = new JMenu("View");
    JMenu sort              = new JMenu("Sort");
    JMenu options           = new JMenu("Options");
    JMenu refreshOptions           =       new JMenu("Refresh every...");
    ButtonGroup sortGroup   = new ButtonGroup();
    options.add(refreshOptions);
    
    JMenuItem viewComposite = new JMenuItem("Composite Timeline");
    viewComposite.addActionListener(
            new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonMgr.doShowCompositeTimeline();
                    }
            });
    view.add(viewComposite);
    
    
    JRadioButtonMenuItem sortByDate = new JRadioButtonMenuItem("Date");
    //sortByDate.setSelected(true);
    sortByDate.setMnemonic(KeyEvent.VK_R);
    sortGroup.add(sortByDate);
    sort.add(sortByDate);

    sortByDate.addActionListener(
                    new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            	buttonMgr.toggle(OrganizeType.JAN_DEC);
                            }
                    });

    JRadioButtonMenuItem sortByAscend = new JRadioButtonMenuItem("Alphabetical");
    sortByAscend.setMnemonic(KeyEvent.VK_O);
    sortGroup.add(sortByAscend);
    sort.add(sortByAscend);
    
    sortByAscend.addActionListener(
                    new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                            	buttonMgr.toggle(OrganizeType.A_Z);
                            }
                    }
                    );
    
    refreshAuto = new JCheckBoxMenuItem("30 seconds");
    refreshAuto.setSelected(false);
    refreshAuto.setMnemonic(KeyEvent.VK_R);
    refreshOptions.add(refreshAuto);
    
    
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
    
    menubar.add(view);
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
        	buttonMgr.systemShutdown();
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
}
