public class Client {
	public static void show(Employee e) {
		System.out.println("-----------------------------------");
		System.out.println("Name:" + e.getName());
		System.out.println("Age:" + e.getAge());
		System.out.println("-----------------------------------");
	}

	public static void main(String[] args) {
		Employee e = new Employee("lili", 25);
		// 创建一个保存器
		CareTaker ct = new CareTaker();
		show(e);
		// 保存一个状态
		ct.setMemento(e.saveMemento());
		e.setName("litianli");
		show(e);
		ct.setMemento(e.saveMemento());
		e.setAge(45);
		show(e);
		ct.setMemento(e.saveMemento());
		// 恢复
		e.restoreMemento(ct.getMemento());
		show(e);
		e.restoreMemento(ct.getMemento());
		show(e);
	}
}