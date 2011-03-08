public class FanOnCommand implements Command {
	// ���������Ҫ�����Ķ���
	private Fan fan;
	
	private boolean change;

	public FanOnCommand(Fan f) {
		change = false;
		fan = f;
	}

	public void execute() {
		if ("off".equals(fan.getState())) {
			change = true;
			// ���ȿ�ʼת��
			fan.startRotate();
		} else {
			// �����Ѿ�ת��,����Ҫ���¿���
			change = false;
			System.out.println("The fan has been started! No Action!");
		}
	}

	public void unExecute() {
		if (change) {
			// ����ֹͣת��
			fan.stopRotate();
			System.out.println(" ==>from undo command!");
		} else {
			// ���֮ǰ������һ��û��Ч������ ��ô�Ͳ���Ҫundo��
			System.out.println("The Fan has been started! Undo nothing!");
		}
	}
}