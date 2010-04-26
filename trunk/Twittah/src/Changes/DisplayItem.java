package Changes;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;

import org.w3c.dom.Element;

import Twitter.Tweeter;

public interface DisplayItem {

		public Tweeter tweeter();
		
		public String source();
		
		public String text();
		
		public Date date();
		
		public String toString();
		
		public abstract Element toElement(org.w3c.dom.Document doc);
		
		
}
