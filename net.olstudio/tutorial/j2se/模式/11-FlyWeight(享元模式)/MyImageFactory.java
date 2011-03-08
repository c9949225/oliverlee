public class MyImageFactory {
	MyImage[] images;

	public MyImageFactory() {
		images = new MyImage[3];
		for (int i = 0; i < 3; i++)
			images[i] = new MyImage("flyweight/lili" + i + ".gif");
	}

	public MyImage getMyImage(int i) {
		return images[i];
	}
}