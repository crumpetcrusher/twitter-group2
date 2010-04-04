package RandomClasses;

import java.util.Date;

public interface FeedItem {

	//public String source;
	//public String text;
	//public Date date;
	
/*	public FeedItem(String newSource, String newText, Date newDate)
	{
		source = newSource;
		text = newText;
		date = newDate;
	}
	
	public FeedItem(FeedItem feedItem)
	{
		this(feedItem.source, feedItem.text, feedItem.date);
	}*/
	
	public String source();	//{	return source;	}
	
	public String text();	//{	return text;	}
	
	public Date date();		//{	return date;	}
	
	//public FeedItem duplicate();
	/*{
		return new FeedItem(this);
	}*/
	
	public String toString();
	/*{
		String value = 		"[Feed Item]" 	+ 							"\r\n"
						+ 	"Source: "		+	source				+	"\r\n"
						+	"Text: "		+	text				+	"\r\n"
						+	"Date: "		+	date.toString()		+	"\r\n";
		return value;
	}*/
}
