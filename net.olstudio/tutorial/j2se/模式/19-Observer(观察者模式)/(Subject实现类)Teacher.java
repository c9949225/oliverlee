import java.util.Vector;

public class Teacher implements Subject {
	
	private String phone;
	// 所有的观察者
	private Vector students;

	public Teacher() {
		phone = "";
		students = new Vector();
	}
	
	// 增加一个观察者
	public void attach(Observer o) {
		students.add(o);
	}
	
	// 删除一个观察者
	public void detach(Observer o) {
		students.remove(o);
	}
	
	// 向观察者发布消息
	public void notice() {
		for (int i = 0; i < students.size(); i++)
			((Observer) students.get(i)).update();
	}
	
	// 调用更新
	public void setPhone(String phone) {
		this.phone = phone;
		notice();
	}

	public String getPhone() {
		return phone;
	}
}