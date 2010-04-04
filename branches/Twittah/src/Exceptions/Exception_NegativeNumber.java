package Exceptions;

public class Exception_NegativeNumber extends RuntimeException{
	
    public Exception_NegativeNumber() {
    	super();
	}
	 
    public Exception_NegativeNumber(String errorMessage) {
    	super(errorMessage);
	}

}
