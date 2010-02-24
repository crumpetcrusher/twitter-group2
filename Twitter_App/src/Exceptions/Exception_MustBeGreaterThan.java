package Exceptions;

import org.apache.commons.lang.NullArgumentException;

public class Exception_MustBeGreaterThan extends RuntimeException {
	
	
	private int min_value;
    public Exception_MustBeGreaterThan() {
    	super();
	}
	 
    public Exception_MustBeGreaterThan(int min_value) {
    	super("\r\nError: The minimum value was not met. \r\nMinimum Value: " + min_value);
    	this.min_value = min_value;
	}
    
    public Exception_MustBeGreaterThan(int min_value, String errorMessage) {
    	super("\r\nError: The minimum value was not met. \r\nMinimum Value: " + min_value + "\r\n\r\nMessage\r\n" + errorMessage);
    	this.min_value = min_value;
	}
    
    public int get_Minimum_Value()
    {
    	int value = -99999;
    	try
    	{
    		value = this.min_value;
    	}
    	catch(NullArgumentException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return value;
    }
}
