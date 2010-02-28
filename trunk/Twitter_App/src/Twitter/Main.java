package Twitter;
// Will need this soon: import javax.swing.*;

/**
 * Main class of the program
 */
public class Main {

	/**
	 * 
	 * main object to run the program
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		testSubscriptions();
		// This will be where we create our GUI object and show it, but
		// for now, it will just be a blank main class
		
	}
	
	public static void testSubscriptions()
	{
		Subscriptions subscriptions = new Subscriptions("bin/subscriptionlist.xml");
		subscriptions.addNewSubscription("14103500");
	}
}
