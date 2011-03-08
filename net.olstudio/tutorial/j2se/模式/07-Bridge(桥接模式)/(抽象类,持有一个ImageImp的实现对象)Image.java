public abstract class Image {
	
	protected ImageImp imptouse;

	public void setImageImp(ImageImp ip) {
		imptouse = ip;
	}
	
	// 抽象方法等待实现
	public abstract void method(String str);
}