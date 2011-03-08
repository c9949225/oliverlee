import java.util.LinkedList;
import java.util.Iterator;

public class AddressBook {
	
	LinkedList people = new LinkedList();
	// µ±∂¡»°xml
	public void addPerson(Person p) {
		people.addLast(p);
	}

	public void print() {
		System.out.println("Address book has " + people.size() + " entries");

		for (Iterator i = people.iterator(); i.hasNext();) {
			Person p = (Person) i.next();
			p.print();
		}
	}
}