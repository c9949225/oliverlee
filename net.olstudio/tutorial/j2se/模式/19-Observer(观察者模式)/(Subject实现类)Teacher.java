import java.util.Vector;

public class Teacher implements Subject {
	
	private String phone;
	// ���еĹ۲���
	private Vector students;

	public Teacher() {
		phone = "";
		students = new Vector();
	}
	
	// ����һ���۲���
	public void attach(Observer o) {
		students.add(o);
	}
	
	// ɾ��һ���۲���
	public void detach(Observer o) {
		students.remove(o);
	}
	
	// ��۲��߷�����Ϣ
	public void notice() {
		for (int i = 0; i < students.size(); i++)
			((Observer) students.get(i)).update();
	}
	
	// ���ø���
	public void setPhone(String phone) {
		this.phone = phone;
		notice();
	}

	public String getPhone() {
		return phone;
	}
}