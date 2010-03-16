package GUI;

import java.awt.*;
import java.util.Date;

import javax.swing.*;

import Exceptions.TweeterException;
import RandomClasses.*;
import Twitter.*;

public class CustomObject1 extends JPanel {

	private static final BorderLayout layout = new BorderLayout();
	private String text = "Empty";

	private JPanel mainContainer = new JPanel(new FlowLayout());
	private JPanel northContainer = new JPanel(new FlowLayout());
	private JTextField source = new JTextField("Unknown");
	private JTextField date = new JTextField("Unknown");
	private JPanel centerContainer = new JPanel();
	private JTextArea body = new JTextArea();
	private JPanel southContainer = new JPanel(new GridLayout(1,0));
	private JButton subscribe = new JButton("Subscribe");
	private JButton view = new JButton("View");
	
	public CustomObject1(FeedItem feedItem)
	{
		setLayout(layout);
		setMinimumSize(new Dimension(200,100));
		add(northContainer, BorderLayout.NORTH);
		//add(southContainer, BorderLayout.SOUTH);
		//add(centerContainer, BorderLayout.CENTER);
		
		source.setText(feedItem.source());
		date.setText(feedItem.date().toString());
		
		body.setText(feedItem.text());
		body.setForeground(Color.BLACK);
		
		northContainer.add(source);
		northContainer.add(date);
		
		centerContainer.add(body);
		
		southContainer.add(subscribe);
		southContainer.add(view);
		
		northContainer.setBackground(Color.BLUE);
		southContainer.setBackground(Color.GRAY);
		
		
		setVisible(true);
	}
	public CustomObject1(String text)
	{
		setLayout(layout);
		setMinimumSize(new Dimension(200,100));
		add(northContainer, BorderLayout.NORTH);
		//add(southContainer, BorderLayout.SOUTH);
		//add(centerContainer, BorderLayout.CENTER);
		
		source.setText(text);
		date.setText("DATE");
		
		body.setText("WFEWJIOEWJFOIEWJOIFEWJOIFEWIOJFEWNCONEWOICNEWON#N@)(#@)#M)(MFEWM)EFW");
		body.setForeground(Color.BLACK);
		
		northContainer.add(source);
		northContainer.add(date);
		
		centerContainer.add(body);
		
		southContainer.add(subscribe);
		southContainer.add(view);
		
		northContainer.setBackground(Color.BLUE);
		southContainer.setBackground(Color.GRAY);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		
	    JFrame frame = new JFrame("Test Frame");
	    frame.setSize(400, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    CustomObject1 button;
			button = new CustomObject1("Asshat");
			 frame.getContentPane().add(button);
	         // Place the component on the application
	                                             // window such that it fills the whole
	                                             // window frame.
	    frame.setVisible(true);
	}

}
