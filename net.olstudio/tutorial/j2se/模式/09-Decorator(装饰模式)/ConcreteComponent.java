// 装饰器和具体对象都实现相同的接口
public class ConcreteComponent implements Component {
	
	String name;

	public void setName(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}
}