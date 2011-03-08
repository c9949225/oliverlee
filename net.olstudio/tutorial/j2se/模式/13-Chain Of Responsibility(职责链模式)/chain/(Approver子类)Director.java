package chain;

public class Director extends Approver {
	public Director(String aName) {
		super(aName);
	}

	public void processRequest(Order o) {
		System.out.println(o.getMessage());
		if (o.getCache() <= 20) {
			System.out.println("I'm a Director!I can deal with it! My name is "
					+ getName() + "!");
		} else {
			String msg = "\nI'm a Director! I can't deal with it! My name is "
					+ getName() + "!";
			o.setMessage(o.getMessage() + msg);
			// ������һ��ְ���Ĵ�����
			getSuccessor().processRequest(o);
		}
	}
}
