public class Mediator {
	private int number;
	private boolean slotFull = false;

	// 加上synchronized,是各个生产者只能按顺序存入数字 
	public synchronized void storeMessage(int num) {
		while (slotFull) // 如果为true的话 表明当前的数字还未被取走  
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = true;
		number = num;
		// 告诉其他线程可以存入新的数字了
		notifyAll();
	}

	public synchronized int retrieveMessage() {
		while (!slotFull) // 如果为false的话 表明当前数字已被取走还未生成新的
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = false;
		// 告诉其他线程可以取数字了
		notifyAll();
		return number;
	}
}