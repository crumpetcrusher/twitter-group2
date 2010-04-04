package Exceptions;

import Twitter.Tweeter;

public class InvalidUserID extends TweeterException{
	
    public InvalidUserID() {
    	super();
	}
	 
    public InvalidUserID(String userIDErrored) {
    	userID = userIDErrored;
	}
    
    public void printError()
    {
    	System.out.println(toString());
    }
    
    public String toString()
    {
    	String value = "";
    	value = userID + " is an invalid ID!";
    	return value;
    }
}
