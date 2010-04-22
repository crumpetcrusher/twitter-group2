//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Class Name : XMLHelper
//    
// Author     : H. Smith (additions by S. Smiesko)
// Date       : 2010-14-04
//
//
// DESCRIPTION
// This class provides a set of utility operations to retrieve different types of Twitter data.
//
// Quick Summary
// --------------
// org.w3c.dom.Document getTweetsByScreenName(String userID): 
// 		This operation returns recent tweets from the user with the specified screen name.
//
// org.w3c.dom.Document getTweetsByKeywords(String[] keywords): 
// 		This operation returns tweets containing the keywords provided.
//
// org.w3c.dom.Document getUserInfo(String userID): 
//		This operation returns the information about the user
//
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package backend;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


public class XMLHelper {
	
	// This is the core of the URL needed to request a user's timeline. To form the complete request URL, just
	// append the user's screen name. For example, to get profhal's stream:
	//
	//     USER_TIMELINE_REQUEST_URL + "profhal"
	//
	private static String USER_TIMELINE_REQUEST_URL_BY_SN = 
   		                                  "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
	
	
	// This is the core of the URL needed to request a user's timeline. To form the complete request URL, just
	// append the user's ID. For example, to get 938319184's stream:
	//
	//     USER_TIMELINE_REQUEST_URL + "938319184"
	//
	private static String USER_TIMELINE_REQUEST_URL_BY_ID = 
   		                                  "http://api.twitter.com/1/statuses/user_timeline.xml?user_id=";
	
	
	// This is the core of the URL needed to request a search by keywords. To form the complete request URL, just
	// append the keywords separated by a '+'. For example, to get tweets with "chocolate cake":
	//
	//     SEARCH_REQUEST_LOCATION_URL + "chocolate+cake"
	//
	private static String SEARCH_REQUEST_LOCATION_URL = "http://search.twitter.com/search.atom?phrase=";


	// This is the core of the URL needed to request a user's information.  To form the complete request URL, just
	// append the user's screen name.  For example, to get profhal's info:
	//
	//		USER_INFO_REQUEST_URL + "profhal"
	//
	private static String USER_INFO_REQUEST_URL_BY_SN =
										  "http://api.twitter.com/1/users/show.xml?screen_name=";
	
	// This is the core of the URL needed to request a user's information.  To form the complete request URL, just
	// append the user's ID.  For example, to get 938319184's info:
	//
	//		USER_INFO_REQUEST_URL + "938319184l"
	//
	private static String USER_INFO_REQUEST_URL_BY_ID =
		  								  "http://api.twitter.com/1/users/show.xml?user_id=";

	private static String LOCAL_SUBSCRIPTION_LIST_LOCATION =
										  "subscriptionlist.xml";
	
	public static org.w3c.dom.Document getDocumentByLocation(String location){
		
		return getXMLDocument(location);
		
	}
	
	
    // This operation returns recent tweets from the user with the specified screen name. If an error is 
	// encountered null is returned.
	//
	public static org.w3c.dom.Document getTweetsByScreenName(String userScreenName) {
		
        return getXMLDocument(USER_TIMELINE_REQUEST_URL_BY_SN + userScreenName);
				
	}

	
    // This operation returns recent tweets from the user with the specified user id. If an error is 
	// encountered null is returned.
	//
	public static org.w3c.dom.Document getTweetsByUserID(String userID) {
		
        return getXMLDocument(USER_TIMELINE_REQUEST_URL_BY_ID + userID);
				
	}

	
    // This operation returns recent tweets containing the set of keywords supplied. If an error is encountered
	// null is returned.
	//
	public static org.w3c.dom.Document getTweetsByKeywords(String[] keywords) {
		
		String keywordString = "";

		for (int k=0; k < keywords.length; ++k) {
			
			keywordString += keywords[k];
			
			keywordString += ( (k == (keywords.length - 1)) ? "" : "+" );
			
		}
		
        return getXMLDocument(SEARCH_REQUEST_LOCATION_URL + keywordString);
				
	}
	
	
	// This operation returns information about the user.  If an error is encountered null is returned
	//
	public static org.w3c.dom.Document getUserInfoByUserSN(String userScreenName) {
		
		return getXMLDocument(USER_INFO_REQUEST_URL_BY_SN + userScreenName);
		
	}
	
	
	// This operation returns information about the user.  If an error is encountered null is returned
	//
	public static org.w3c.dom.Document getUserInfoByUserID(String userID) {
		
		return getXMLDocument(USER_INFO_REQUEST_URL_BY_ID + userID);
		
	}
	
	
	// This operation tries to retrieve the XML Document stored at the specified URL. If any error is
	// encountered the operation returns null.
	//
	private static org.w3c.dom.Document getXMLDocument(String url) {
		
		Document document = null;
		
		DocumentBuilderFactory builderFactory;
		DocumentBuilder parser;
		
		// Try to load the document at the specified filepath into a DOM structure.
        //
        try {

            builderFactory = DocumentBuilderFactory.newInstance();

            parser = builderFactory.newDocumentBuilder();

            document = parser.parse(url);

        } catch (ParserConfigurationException p) {
        	
            System.out.println("Error creating DOM parser.");
            System.out.println("   " + p.getMessage());
        
        } catch (SAXException s) {
        
        	System.out.println("XML document returned is not well-formed.");
        	System.out.println("   URL : " + url);
            System.out.println("   " + s.getMessage());
        
        } catch (IOException i) {
        
        	System.out.println("Error accessing the stream.");
        	System.out.println("   URL : " + url);
            System.out.println("   " + i.getMessage());
        
        } 		
		
        return document;
		
		
	}

}
