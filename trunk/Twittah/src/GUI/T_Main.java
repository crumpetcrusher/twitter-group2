

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
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;

	import org.w3c.dom.Document;
	import org.w3c.dom.Element;

	import backend.ButtonManager;
	import backend.SubscriptionsManager;
	import backend.TimelinesManager;
	import backend.XMLHelper;

	public class T_Main extends JPanel{

	        /**
	         * 
	         */
	        private static final long serialVersionUID = 5964038338276344070L;

	        
	        public static void main(String[] args) throws Exception {               
	        	init();
	                
	        }
	        
	        public static void init()
	        {

	    	    new RootGUI();

	        }
	        
	        private void writeSettings(Element root, Document doc)
	        {
	        	
	        }
	        


}
