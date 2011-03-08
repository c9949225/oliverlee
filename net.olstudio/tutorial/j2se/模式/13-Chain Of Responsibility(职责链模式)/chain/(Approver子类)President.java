package chain;

public class President extends Approver {
	public President(String aName) {
		super(aName);
	}

	public void processRequest(Order o) {
		System.out.println(o.getMessage());
		System.out.println("I'm a President!I deal all thing! My name is "
				+ getName() + "!");
	}
}
