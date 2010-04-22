package Changes;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;

import Twitter.Tweeter;

public interface DisplayItem {

		public Tweeter tweeter();
		
		public String source();
		
		public String text();
		
		public Date date();
		
		public String toString();
}
