public class Client {
	public static void main(String[] args) {
		Component c = new ConcreteComponent();
		Component d = new Decorator(c);
		c.setName("Lili");
		System.out.println(c.getName());
		d.setName("Lili");
		System.out.println(d.getName());
	}
}