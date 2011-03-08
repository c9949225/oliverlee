package chain;

public abstract class Approver {
	String name;
	// 职责链上的每个职责点都包含对下一个职责点的引用
	Approver approver;// 公共父类持有下一责任者

	public Approver(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}
	
	// 设置下一个职责点
	public void setSuccessor(Approver approver) {
		this.approver = approver;
	}

	public Approver getSuccessor() {
		return approver;
	}
	
	// 每个具体的职责点实现该方法来具体化出来的动作
	// 被处理的对象作为参数传入
	public abstract void processRequest(Order od);
}
