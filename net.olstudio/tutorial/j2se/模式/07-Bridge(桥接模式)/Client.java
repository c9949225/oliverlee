/*
 * ���ۣ�
 * 
 * Bridgeģʽ��Ч��ʹ��������һ�����巽����ʵ�ֿ��Զ����仯��
 * �빤��������ͬ���ǣ������ķ���ʵ�ֿ��Ա�����
 * 
 */

public class Client {
	public static void main(String[] args) {
		
		// �̳г�����,����ImageImp��ʵ�������
		// bmp���в�ͬ�Ķ�����в�ͬ����Ϊ
		BMPImage bmp = new BMPImage(); 
		
		// ImageImp��ʵ����
		ImageImp winimp = new WinImp();
		
		// 1, ����һ������
		bmp.setImageImp(winimp);
		bmp.method("Paint ==>");
		
		ImageImp uniximp = new UnixImp();
		
		// 2, ��������һ������
		bmp.setImageImp(uniximp);
		bmp.method("Paint ==>");
	}
}