public class Student implements Observer {
	
	private String name;
	private String phone;
	
	// 观察者也要保存一个要观察的对象
	private Teacher teacher;

	// 在构造对象的时候传入一个要观察的对象
	public Student(String name, Teacher t) {
		this.name = name;
		teacher = t;
	}

	public void show() {
		System.out.println("Name:" + name + "\nTeacher'sphone:" + phone);
	}
	
	// 要实现一个事件更新的方法
	public void update() {
		// 将被观察者的属性更新到自己的属性
		// 那么这里为什么不可以直接用被观察者的属性呢？
		// 反正都要保存被观察者
		// 我觉得是因为这个例子的逻辑是保存老师的号码
		// 在有些情况下是不需要保持被观察者的实例的
		phone = teacher.getPhone();
	}
}