import java.util.Vector;

public class Controller implements Iterator {
	private int current = 0;
	Vector channel;

	public Controller(Vector v) {
		channel = v;
	}

	public Item first() {
		current = 0;
		return (Item) channel.get(current);
	}

	public Item next() {
		current++;
		return (Item) channel.get(current);
	}

	public Item currentItem() {
		return (Item) channel.get(current);
	}

	public boolean isDone() {
		return current >= channel.size() - 1;
	}
}