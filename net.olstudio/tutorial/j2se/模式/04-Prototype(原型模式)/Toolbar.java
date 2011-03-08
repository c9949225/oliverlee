import java.util.HashMap;

public class Toolbar {
	
	private HashMap tools = null;
	// �����������Ӷ���
	public Toolbar() {
		tools = new HashMap();
		tools.put("circle", new Circle());
		tools.put("rectangle", new Rectangle());
	}

	public Object getClone(String key) {
		Object obj = tools.get(key);
		if (obj != null)
			return ((Prototype) obj).clone();
		return null;
	}
}