package Outdated;

public final class Twitter_Get_Types {
	
	public static final String Twitter = "http://twitter.com/";
	public static final Twitter_Get_Types friends_timeline = new Twitter_Get_Types(Twitter + "statuses/friends_timeline.xml");
	public static final Twitter_Get_Types home_timeline = new Twitter_Get_Types(Twitter + "statuses/home_timeline.xml");
	public static Twitter_Get_Types search = new Twitter_Get_Types(Twitter + "search.xml");
	private String url = null;
	
	public Twitter_Get_Types(String url_input)
	{
		this.url = url_input;
	}
	
	//public parameters()
	
	public String toString()
	{
		return url;
	}
}
