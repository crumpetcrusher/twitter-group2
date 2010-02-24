package Outdated;

/**
 * This is the timeline_type object.
 * <dl>
 * <dt>Pre-defined Objects:
 * 		<dd>{@link Timeline_Type#_public _public}
 * 		<dd>{@link Timeline_Type#_home _home}
 * 		<dd>{@link Timeline_Type#_friends _friends}
 * 		<dd>{@link Timeline_Type#_user _user}
 * 		<dd>{@link Timeline_Type#_mentions _mentions}
 * 		<dd>{@link Timeline_Type#_retweeted_by_me _retweeted_by_me}
 * 		<dd>{@link Timeline_Type#_retweeted_to_me _retweeted_to_me}
 * 		<dd>{@link Timeline_Type#_retweets_of_me _retweets_of_me}
 * </dl>
 * 
 * @author Rick
 * @version 2/2/2009
 * 
 */
public class Timeline_Type {
	
	//Pre-defined Class
	
	/**
	 * Returns the 20 most recent statuses from non-protected users who have set a custom user icon.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-public_timeline">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _public = new Timeline_Type("public_timeline");
	/**
	 * Returns the 20 most recent statuses, including retweets, posted by the authenticating user and that user's friends. This is the equivalent of /timeline/home on the Web.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-home_timeline">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _home = new Timeline_Type("home_timeline");
	/**
	 * Returns the 20 most recent statuses posted by the authenticating user and that user's friends. This is the equivalent of /timeline/home on the Web.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-friends_timeline">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _friends = new Timeline_Type("friends_timeline");
	/**
	 * Returns the 20 most recent statuses posted from the authenticating user. It's also possible to request another user's timeline via the id parameter. This is the equivalent of the Web /<user> page for your own user, or the profile page for a third party.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-user_timeline">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _user = new Timeline_Type("user_timeline");
	/**
	 * Returns the 20 most recent mentions (status containing @username) for the authenticating user.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-mentions">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _mentions = new Timeline_Type("mentions");
	/**
	 * Returns the 20 most recent retweets posted by the authenticating user.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-retweeted_by_me">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _retweeted_by_me = new Timeline_Type("retweeted_by_me");
	/**
	 * Returns the 20 most recent retweets posted by the authenticating user's friends.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-retweeted_to_me">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _retweeted_to_me = new Timeline_Type("retweeted_to_me");
	/**
	 * Returns the 20 most recent tweets of the authenticated user that have been retweeted by others.
	 * 
	 * <dl>
	 * <dt>Link:
	 * <dd><a href="http://apiwiki.twitter.com/Twitter-REST-API-Method%3A-statuses-retweets_of_me">Documentation</a>
	 * </dl>
	 */
	public static final Timeline_Type _retweets_of_me = new Timeline_Type("retweeted_of_me");
	
	//Class variables
	
	/**
	 * This is the type of time line to be used.
	 * </br>Based on Timeline Methods in Twitter's <a href="http://apiwiki.twitter.com/Twitter-API-Documentation">API Documentation</a>
	 */
	private String type = null;
	
	//Constructors
	
	/**
	 * This is a constructor method that will a take a time line input (these are pre-defined above)
	 * 
	 * @author Rick Humes
	 * @param type Type of time line that you wish to look at
	 * @see Timeline_Type
	 */
	public Timeline_Type(String type)
	{
		this.type = type;
	}
	
	//Methods
	
    /**
     * This returns the time line type.
     * 
     * @author Rick Humes
     * @return userName Peron's username.
     */
	public String toString()
	{
		return this.type;
	}
	
}