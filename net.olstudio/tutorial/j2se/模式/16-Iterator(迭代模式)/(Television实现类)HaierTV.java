import java.util.Vector;

public class HaierTV implements Television {
	private Vector channel;

	// ��Ӷ��Ƶ��
	public HaierTV() {
		channel = new Vector();
		channel.addElement(new Item("channel 1"));
		channel.addElement(new Item("channel 2"));
		channel.addElement(new Item("channel 3"));
		channel.addElement(new Item("channel 4"));
		channel.addElement(new Item("channel 5"));
		channel.addElement(new Item("channel 6"));
		channel.addElement(new Item("channel 7"));
	}

	public Vector getChannel() {
		return channel;
	}
	
	// ���ص�����
	public Iterator createIterator() {
		return new Controller(channel);
	}
}