package chain;
public class VicePresident extends Approver{
	public VicePresident(String aName){
		super(aName);
	}
	public void processRequest(Order o){
		if(o.getCache()<40){
			System.out.println(o.getMessage());
			System.out.println("I'm a VicePresident! I can deal with it! My name is "+getName()+"!");
		}
		else{
			String msg = "\nI'm a VicePresident! I can't deal with it! My name is "+getName()+"!";
			o.setMessage(o.getMessage()+msg);
			getSuccessor().processRequest(o);
		}
	}
}
