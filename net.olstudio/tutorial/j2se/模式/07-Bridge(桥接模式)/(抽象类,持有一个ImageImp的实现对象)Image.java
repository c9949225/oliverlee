public abstract class Image {
	
	protected ImageImp imptouse;

	public void setImageImp(ImageImp ip) {
		imptouse = ip;
	}
	
	// ���󷽷��ȴ�ʵ��
	public abstract void method(String str);
}