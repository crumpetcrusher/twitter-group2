package Exceptions;

import Twitter.Tweeter;

public class UserProtectedException extends RuntimeException{
	
    public UserProtectedException() {
    	super();
	}
	 
    public UserProtectedException(Tweeter tweeter) {
    	super(tweeter.getScreenName() + "'s tweets are protected!");
	}

}
