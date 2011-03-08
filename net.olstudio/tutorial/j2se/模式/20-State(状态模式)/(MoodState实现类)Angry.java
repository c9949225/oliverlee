public class Angry implements MoodState {
	// 每个状态对象都要保存自己所附着的主对象
	Person p;

	public Angry(Person p) {
		this.p = p;
	}

	public void doSomething() {
		System.out.println("I'm angry!");
	}

	public void changeState() {
		// 将自己的Person传入新的MoodState
		p.setState(new Happy(p));
	}
}