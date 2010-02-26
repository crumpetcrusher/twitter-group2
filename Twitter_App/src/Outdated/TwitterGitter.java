package Outdated;
import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class TwitterGitter {
	
	private Credentials login_information = null;
	private HttpClient client = null;
	
	
	public TwitterGitter()
	{
		client = new HttpClient();
	}
	
	public boolean setLogin(String username, String password) throws NullPointerException, HttpException, IOException 
	{
		if ((username == null)||(password == null)) 
		{
		    throw new RuntimeException("User and/or password is not defined!");
		}
		login_information = new UsernamePasswordCredentials(username, password);
		return true;
	}
	
	/*public XML getXML() throws NullPointerException, HttpException, IOException {
		
		return false;
	}*/
}
