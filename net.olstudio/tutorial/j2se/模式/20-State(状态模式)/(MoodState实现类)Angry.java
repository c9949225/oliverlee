public class Angry implements MoodState {
	// ÿ��״̬����Ҫ�����Լ������ŵ�������
	Person p;

	public Angry(Person p) {
		this.p = p;
	}

	public void doSomething() {
		System.out.println("I'm angry!");
	}

	public void changeState() {
		// ���Լ���Person�����µ�MoodState
		p.setState(new Happy(p));
	}
}