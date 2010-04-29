package Twitter;

//Import statements
import java.util.Date;
import javax.swing.ImageIcon;
import Changes.DisplayItem;


//This is the tweet object!

public class Tweet implements DisplayItem
{
	
// Class Variables
    
    //The tweeter that did the tweeting
    private Tweeter tweeter = null;
    
    //Tweet date
    private Date tweetDate = null;

    //Tweet text
    private String tweetText = null;
    
    //How the tweet was tweeted
    private String tweetMethod = null;
    
    
// Class Constructors
    
    
    //This class constructor allows for creation of a tweet, passing in all data.
    public Tweet(Tweeter newTweeter, String newText, Date newDate, String newMethod)
    {
        //The setting of the variables
    	tweeter = newTweeter;
    	tweetText = newText;
    	tweetDate = newDate;
    	tweetMethod = newMethod;
    }
    

//Class Methods
    
    //The following method override DisplayItem
    
    //Returns the date at which this tweet was tweeted
    @Override
    public Date date() 
    {
        return tweetDate;
    }
    
    //Returns the method by which the tweet was made
    @Override
    public String source()
    {
        return tweetMethod;
    }
    
    //Returns the text of the tweet
    @Override
    public String text()
    {
        return tweetText;
    }

    //Returns the tweeter's icon
    @Override
    public ImageIcon icon() {
        return tweeter.icon();
    }
}