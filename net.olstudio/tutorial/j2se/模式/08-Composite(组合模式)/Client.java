public class Client {
	public static void main(String[] args) {
		Component root = new Composite("root");
		Component composite = new Composite("composite");
		Component leaf1 = new Leaf("leaf1", 20);
		Component leaf2 = new Leaf("leaf2", 40);
		root.add(leaf1);
		composite.add(leaf2);
		root.add(composite);
		String str = "Leaf1's size is " + leaf1.getSize();
		str += "\nleaf2's size is " + leaf2.getSize();
		str += "\nComposite's size is " + composite.getSize();
		str += "\nRoot's size is " + root.getSize();
		System.out.println(str);
	}
}