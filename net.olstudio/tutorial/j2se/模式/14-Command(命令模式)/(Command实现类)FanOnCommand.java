public class FanOnCommand implements Command {
	// 保存该命令要操作的对象
	private Fan fan;
	
	private boolean change;

	public FanOnCommand(Fan f) {
		change = false;
		fan = f;
	}

	public void execute() {
		if ("off".equals(fan.getState())) {
			change = true;
			// 电扇开始转动
			fan.startRotate();
		} else {
			// 电扇已经转动,不需要重新开启
			change = false;
			System.out.println("The fan has been started! No Action!");
		}
	}

	public void unExecute() {
		if (change) {
			// 电扇停止转动
			fan.stopRotate();
			System.out.println(" ==>from undo command!");
		} else {
			// 如果之前发送了一个没生效的命令 那么就不需要undo了
			System.out.println("The Fan has been started! Undo nothing!");
		}
	}
}