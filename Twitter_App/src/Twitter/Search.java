package Twitter;
import java.io.*;
import java.util.ArrayList;

import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import Exceptions.Exception_NegativeNumber;
//import 


public class Search {
	
	private static final String search = "http://twitter.com/search.atom?";
	private ArrayList<String> parameters = new ArrayList<String>();
	StringBuffer xml = new StringBuffer();
	

	
	public Search()
	{
		
	}
	
	private void process_Search() throws Exception
	{
		try{
			RequestEntity requestEntity = new StringRequestEntity(xml.toString(), "application/atom+xml", "UTF-8");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void tweets_per_page(int num) throws Exception_NegativeNumber
	{
		for(String parameter:parameters)
			if(parameter.contains("rpp="))
				parameters.remove(parameter);
		
		if(num < 0) 
			throw new Exception_NegativeNumber("tweets_per_age parameter must be 1 or greater.");
		parameters.add("rpp=" + num);
	}
	
	

}
