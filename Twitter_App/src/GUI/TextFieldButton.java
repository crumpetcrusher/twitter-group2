package GUI;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
 
public class TextFieldButton extends JPanel {
	JTextField field;
	JButton button;
	public TextFieldButton()  {
		field = new JTextField(10);
		button = new JButton("test");
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(field);
		add(button);
		field.setBorder(null);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setBackground(Color.white);		
	}
	
	public static void main(String[] args)  {
		TextFieldButton tfb = new TextFieldButton();
		JFrame frame = new JFrame();
		frame.getContentPane().add(tfb);
		frame.pack();
		frame.show();
	}
}