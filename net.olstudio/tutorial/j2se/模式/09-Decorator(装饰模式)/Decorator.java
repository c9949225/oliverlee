
// 装饰器和具体对象都实现相同的接口
public class Decorator implements Component {
	
	private Component component;// 持有被修饰的对象实例，基本数据从该实例取得

	public Decorator(Component c) {
		component = c;
	}

	public void setName(String aName) {
		component.setName(aName);
	}

	public String getName() {
		return "Name :" + component.getName();
	}
}