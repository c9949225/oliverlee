package chain;
public class Client{
	public static void main(String[] args){
		Approver lili1  = new Director("lili1");
		Approver lili2 = new VicePresident("lili2");
		Approver lili3 = new President("lili3");
		lili1.setSuccessor(lili2); // 第一个持有其下一个责任者
		lili2.setSuccessor(lili3); // 第二个持有其下一个，最后的责任者
		
		Order o =new Order(10);
		lili1.processRequest(o);// 由第一个开始处理，自动传给下一个，知道最后
		o = new Order(30);
		lili1.processRequest(o);
		
		o = new Order(50);
		lili1.processRequest(o);
	}
}	
