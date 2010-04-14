package Exceptions;

import Twitter.Tweeter;

public class UserProtectedException extends TweeterException{
	
    public UserProtectedException() {
    	super();
	}
	 
    public UserProtectedException(Tweeter tweeterErrored) {
    	tweeter = tweeterErrored;
	}
    
    public void printError()
    {
    	System.out.println(toString());
    }
    
    public String toString()
    {
    	String value = "";
    	value = tweeter.getScreenName() + "'s tweets are protected!";
    	return value;
    }

}
