import java.util.Vector;

public class CareTaker {
	private Vector v;
	// ¼ÇÂ¼Õ»¶¥
	private int current;

	public CareTaker() {
		current = -1;
		v = new Vector();
	}

	public void setMemento(Memento mem) {
		for (int i = current + 1; i < v.size(); i++)
			v.remove(i);
		current++;
		v.add(mem);
	}

	public Memento getMemento() {
		if (current > 0) {
			current--;
			return (Memento) v.get(current);
		}
		return null;
	}
}