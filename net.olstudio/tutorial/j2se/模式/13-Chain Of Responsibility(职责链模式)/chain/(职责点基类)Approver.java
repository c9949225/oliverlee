package chain;

public abstract class Approver {
	String name;
	// ְ�����ϵ�ÿ��ְ��㶼��������һ��ְ��������
	Approver approver;// �������������һ������

	public Approver(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}
	
	// ������һ��ְ���
	public void setSuccessor(Approver approver) {
		this.approver = approver;
	}

	public Approver getSuccessor() {
		return approver;
	}
	
	// ÿ�������ְ���ʵ�ָ÷��������廯�����Ķ���
	// ������Ķ�����Ϊ��������
	public abstract void processRequest(Order od);
}
