/*
 * 
 * 总结：原型模式在生成复杂对象比较苦难的环境中比较适用，
 * 通过克隆已有对象来实现创建新的对象，节省了时间和空间。
 * 
 */
public class Client {
	public static void main(String[] args) {
		Toolbar tb = new Toolbar();
		Command c = (Command) tb.getClone("circle");
		c.draw();
		c = (Command) tb.getClone("rectangle");
		c.draw();
	}
}