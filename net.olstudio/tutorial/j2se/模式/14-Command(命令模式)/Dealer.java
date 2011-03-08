import java.util.Vector;

public class Dealer {
	private Vector v;

	public Dealer() {
		v = new Vector();
	}

	public void deal(Command command) {
		v.addElement(command);
		command.execute();
	}

	public boolean unDeal() {
		if (v.size() > 0) {
			Command command = (Command) v.get(v.size() - 1);
			command.unExecute();
			v.remove(v.size() - 1);
			return true;
		} else {
			return false;
		}
	}
}