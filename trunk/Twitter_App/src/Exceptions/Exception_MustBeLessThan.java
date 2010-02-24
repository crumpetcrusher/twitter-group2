package Exceptions;
import org.apache.commons.lang.NullArgumentException;

public class Exception_MustBeLessThan extends RuntimeException{

		private int max_value;
	    public Exception_MustBeLessThan() {
	    	super();
		}
		 
	    public Exception_MustBeLessThan(int max_value) {
	    	super("\r\nError: The maximum value was exceeded. \r\nMaximum Value: " + max_value);
	    	this.max_value = max_value;
		}
	    
	    public Exception_MustBeLessThan(int max_value, String errorMessage) {
	    	super("\r\nError: The maximum value was exceeded. \r\nMaximum Value: " + max_value + "\r\n\r\nMessage\r\n" + errorMessage);
	    	this.max_value = max_value;
		}
	    
	    public int get_Maximum_Value()
	    {
	    	int value = -99999;
	    	try
	    	{
	    		value = this.max_value;
	    	}
	    	catch(NullArgumentException e)
	    	{
	    		System.out.println(e.getMessage());
	    	}
	    	return value;
	    }
}
