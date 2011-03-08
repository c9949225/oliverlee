import java.util.HashMap;
import java.util.Iterator;
public class Person {
  private int id;
  private String category;
  private String name;
  private HashMap emails = new HashMap();
  
  // Oliver �� 2008-5-24 ����05:25:28 ����ע��:
  // ���ڵ����������Ϊ�����,ͨ��addSetProperties�����趨
  public void setId(int id) {
      this.id = id;
  }
  public void setCategory(String category) {
      this.category = category;
  }
  
  // ���ڵ�������趨Ϊ�����,ͨ��addCallMethod����
  // ���Զ�����ҲҪ���ԣ���ҪaddBeanPropertySetter
  public void setName(String name) {
      this.name = name;
  }
  
  // ͬname����ʱ��Ҫһһָ��addEmail�Ĳ���ֵ����Դ��
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