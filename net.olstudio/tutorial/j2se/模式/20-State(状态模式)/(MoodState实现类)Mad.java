public class Mad implements MoodState {
	// ÿ��״̬����Ҫ�����Լ������ŵ�������
	Person p;

	public Mad(Person p) {
		this.p = p;
	}

	public void doSomething() {
		System.out.println("I'm Mad");
	}

	public void changeState() {
		// ���Լ���Person�����µ�MoodState
		p.setState(new Angry(p));
	}
}