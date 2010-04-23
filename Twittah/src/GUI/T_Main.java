package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class T_Main {
	
	public static void main(String[] args) throws Exception
	{		
		
		Main main = new Main();
		
		JFrame frame = new JFrame("Twittah!");
		
		
		// Menubar
		//
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		menubar.add(file);
    
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
