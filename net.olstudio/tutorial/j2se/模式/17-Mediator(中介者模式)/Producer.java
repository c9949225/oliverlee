public class Producer extends Thread {
	private Mediator med;
	private int id;
	private static int num = 1;

	public Producer(Mediator m) {
		med = m;
		id = num++;
	}

	public void run() {
		int times = 0;
		int num;
		while (times < 20) {
			times++;
			// 联系生产20个数字给中介
			med.storeMessage(num = (int) (Math.random() * 100));
			System.out.println("p" + id + "-" + num + " ");
		}
	}
}