import java.util.HashMap;
import java.util.Iterator;
public class Person {
  private int id;
  private String category;
  private String name;
  private HashMap emails = new HashMap();
  
  // Oliver 于 2008-5-24 下午05:25:28 作出注释:
  // 将节点的属性设置为类变量,通过addSetProperties方法设定
  public void setId(int id) {
      this.id = id;
  }
  public void setCategory(String category) {
      this.category = category;
  }
  
  // 将节点的内容设定为类变量,通过addCallMethod方法
  // 想自动调用也要可以，需要addBeanPropertySetter
  public void setName(String name) {
      this.name = name;
  }
  
  // 同name，此时还要一一指定addEmail的参数值的来源。
  public void addEmail(String type, String address) {
      emails.put(type, address);
  }
  
  public void print() {
      System.out.println("Person #" + id);
      System.out.println("  category=" + category);
      System.out.println("  name=" + name);
      for(Iterator i = emails.keySet().iterator(); i.hasNext(); ) {
          String type = (String) i.next();
          String address = (String) emails.get(type);
          System.out.println("  email (type " + type + ") : " + address);
      }
  }
}