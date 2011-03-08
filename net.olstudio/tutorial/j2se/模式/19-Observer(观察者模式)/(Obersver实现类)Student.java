public class Student implements Observer {
	
	private String name;
	private String phone;
	
	// �۲���ҲҪ����һ��Ҫ�۲�Ķ���
	private Teacher teacher;

	// �ڹ�������ʱ����һ��Ҫ�۲�Ķ���
	public Student(String name, Teacher t) {
		this.name = name;
		teacher = t;
	}

	public void show() {
		System.out.println("Name:" + name + "\nTeacher'sphone:" + phone);
	}
	
	// Ҫʵ��һ���¼����µķ���
	public void update() {
		// �����۲��ߵ����Ը��µ��Լ�������
		// ��ô����Ϊʲô������ֱ���ñ��۲��ߵ������أ�
		// ������Ҫ���汻�۲���
		// �Ҿ�������Ϊ������ӵ��߼��Ǳ�����ʦ�ĺ���
		// ����Щ������ǲ���Ҫ���ֱ��۲��ߵ�ʵ����
		phone = teacher.getPhone();
	}
}