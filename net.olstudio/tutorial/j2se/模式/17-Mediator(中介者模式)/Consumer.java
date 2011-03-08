public class Consumer extends Thread {
	private Mediator med;
	// 根据num产生该消费者的id
	private int id;
	private static int num = 1;

	public Consumer(Mediator m) {
		med = m;
		id = num++;
	}

	public void run() {
		int times = 0;
		while (times < 10) {
			// 从中介处连续取10次
			times++;
			System.out.println("c" + id + "-" + med.retrieveMessage() + " ");
		}
	}
}