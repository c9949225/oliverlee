import org.apache.commons.digester.Digester;

/**
 * Usage: java Example1 example.xml
 */
public class AddressBookDigester {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			System.exit(-1);
		}
		String filename = args[0];
		// 创建一个Digester实例
		Digester d = new Digester();
		// 创建AddressBook实例，并将其压入栈顶。
		// 这样就可以使用它的方法来实现规则了
		AddressBook book = new AddressBook();
		d.push(book);
		// 增加规则
		addRules(d);
		
		try {
			java.io.File srcfile = new java.io.File(filename);
			// 处理输入的xml文件
			d.parse(srcfile);
		} catch (java.io.IOException ioe) {
			System.out.println("Error reading input file:" + ioe.getMessage());
			System.exit(-1);
		} catch (org.xml.sax.SAXException se) {
			System.out.println("Error parsing input file:" + se.getMessage());
			System.exit(-1);
		}

		// 将解析出的地址数据打印出来
		book.print();
	}

	private static void addRules(Digester d) {
		// 当遇到<person>时，创建类Person的一个实例，并将其压入栈顶
		d.addObjectCreate("address-book/person", Person.class);

		// 将<person>标签的属性(attribute)与栈顶Person类对象的属性(property)设置方法根据各自的名字进行映射， (例如，将标签属性id与属性设置方法setId进行映射，将标签属性category与属性设置方法setCategory进行映射)，然后将属性的值作参数传递给执行相应的方法。
		// 如果某标签属性没法通过名字找到相应的属性设置方法，则此标签属性被忽略(如example.xml中第一个<person>的try属性)。
		
		// Oliver 于 2008-5-24 下午05:33:24 作出注释:
		// 用于配置节点属性
		d.addSetProperties("address-book/person");

		// 调用第二栈顶对象(AddressBook实例)的addPerson方法，以栈对象(Person实例)的对象为参数
		d.addSetNext("address-book/person", "addPerson");

		// 当遇到<person>的子元素<name>时，调用栈顶对象(Person实例)的setName方法。
		// 此处addCallMethod方法的第一参数是规则，第二个参数是方法的名字，第三个是参数的数量(为0时，表示只有一个参数，且参数的值是元素的内容)
		d.addCallMethod("address-book/person/name", "setName", 0);

		// 当遇到<person>的子元素<email>时，调用栈顶对象(Person实例)的addEmail方法,addEmail 方法有两个参数，
		// 取值分别来自<email>的属性type的值和<email>本身的内容。
		// 此处addCallParam方法的第一参数是规则，第二个参数是指明被调用方法(addEmail)参数的序号，第三个是参数为字符串时指属性的名字)
		d.addCallMethod("address-book/person/email", "addEmail", 2);
		d.addCallParam("address-book/person/email", 0, "type"); // <email>节点的属性type的值作为参数
		d.addCallParam("address-book/person/email", 1); // <email>本身的内容作为方法的第二个参数
	}

	private static void usage() {
		System.out.println("Usage: java Example1 example.xml");
	}
}

// 输出:

//Address book has 2 entries
//Person #1
//  category=acquaintance
//  name=Gonzo
//  email (type business) : gonzo@muppets.com
//Person #2
//  category=rolemodel
//  name=Kermit
//  email (type business) : kermit@muppets.com
//  email (type home) : kermie@acme.com