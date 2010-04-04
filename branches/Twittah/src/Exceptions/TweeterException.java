package Exceptions;

import Twitter.Tweeter;

public abstract class TweeterException extends Exception {
	
	
	protected Tweeter tweeter = null;
	
	protected String userID = null;
	
	public abstract void printError();
	
	public abstract String toString();
    
}
