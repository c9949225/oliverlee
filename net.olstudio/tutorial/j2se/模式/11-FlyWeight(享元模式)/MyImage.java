import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class MyImage {
	
	private Image image;

	public MyImage(String file) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File f = new File(file);
		if (f.exists()) {
			image = toolkit.getImage(file);
		} else {
			System.out.println("File unable to load!");
		}
	}

	public void draw(Graphics g, int x, int y, String name, ImageObserver obs) {
		g.drawImage(image, x, y, 30, 30, obs);
		g.drawString(name, x, y + 40);
	}
}