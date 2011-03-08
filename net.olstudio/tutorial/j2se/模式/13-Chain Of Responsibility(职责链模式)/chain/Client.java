package chain;
public class Client{
	public static void main(String[] args){
		Approver lili1  = new Director("lili1");
		Approver lili2 = new VicePresident("lili2");
		Approver lili3 = new President("lili3");
		lili1.setSuccessor(lili2); // ��һ����������һ��������
		lili2.setSuccessor(lili3); // �ڶ�����������һ��������������
		
		Order o =new Order(10);
		lili1.processRequest(o);// �ɵ�һ����ʼ�����Զ�������һ����֪�����
		o = new Order(30);
		lili1.processRequest(o);
		
		o = new Order(50);
		lili1.processRequest(o);
	}
}	
