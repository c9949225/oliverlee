public class Happy implements MoodState {
	
	// ÿ��״̬����Ҫ�����Լ������ŵ�������
	Person p;

	public Happy(Person p) {
		this.p = p;
	}

	public void doSomething() {
		System.out.println("I'm happy!");
	}

	public void changeState() {
		// ���Լ���Person�����µ�MoodState
		p.setState(new Mad(p));
	}
}