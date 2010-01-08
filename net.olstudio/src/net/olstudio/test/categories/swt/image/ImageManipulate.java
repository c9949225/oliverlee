package net.olstudio.test.categories.swt.image;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class ImageManipulate {

	private void init() {
		String fileName = "source.jpg";
		String saveName = "saved.jpg";
		
		ImageLoader loader = new ImageLoader();
		ImageData[] imageData = loader.load(fileName);
		
		if (imageData.length > 0) {
			Image newImage = new Image(null, imageData[0]);
		}
		
		// 对newImage进行操作...
		// loader.data[0] =
		// newImage.getImageData();
		// loader.save(saveName, SWT.IMAGE_BMP);}
	}

	public static void main(String[] args) {

	}
}
