public class Mediator {
	private int number;
	private boolean slotFull = false;

	// ����synchronized,�Ǹ���������ֻ�ܰ�˳��������� 
	public synchronized void storeMessage(int num) {
		while (slotFull) // ���Ϊtrue�Ļ� ������ǰ�����ֻ�δ��ȡ��  
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = true;
		number = num;
		// ���������߳̿��Դ����µ�������
		notifyAll();
	}

	public synchronized int retrieveMessage() {
		while (!slotFull) // ���Ϊfalse�Ļ� ������ǰ�����ѱ�ȡ�߻�δ�����µ�
			try {
				wait();
			} catch (InterruptedException e) {
			}
		slotFull = false;
		// ���������߳̿���ȡ������
		notifyAll();
		return number;
	}
}