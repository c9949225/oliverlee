import javax.swing.*;
import java.awt.*;

public class ImageProxy implements Icon {
	
	public ImageProxy(String name) {
		this.name = name;
		image = null;
	}

	public int getIconHeight() {
		ensureImageLoaded();
		return image.getIconHeight();
	}

	public int getIconWidth() {
		ensureImageLoaded();
		return image.getIconWidth();
	}

	public void paintIcon(Component component, Graphics graphics, int n, int n1) {
		ensureImageLoaded();
		image.paintIcon(component, graphics, n, n1);
	}

	private void ensureImageLoaded() {
		if (image == null) {
			System.out.println("Loading " + name);
			image = new ImageIcon("proxy/" + name);
		}
	}

	private String name;
	private ImageIcon image;
}
