package Twitter;

/**
 * This is the Source object.
 * 
 * @author Rick
 * @version 1/16/2009
 */
public class Source {
	
	public static final Source Tweet_Deck = new Source("Tweet Deck");
	public static final Source Seesmic = new Source("Seesmic");
	public static final Source IPhone = new Source("IPhone");
	
	private String source;
	
	/**
	 * @author Rick Humes
	 * @param source The source of the tweet.
	 */
	public Source(String source)
	{
		this.source = source;
	}
	
	public String toString()
	{
		return this.source;
	}

}
