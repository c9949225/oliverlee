public class Consumer extends Thread {
	private Mediator med;
	// ����num�����������ߵ�id
	private int id;
	private static int num = 1;

	public Consumer(Mediator m) {
		med = m;
		id = num++;
	}

	public void run() {
		int times = 0;
		while (times < 10) {
			// ���н鴦����ȡ10��
			times++;
			System.out.println("c" + id + "-" + med.retrieveMessage() + " ");
		}
	}
}