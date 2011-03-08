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
		// ����һ��Digesterʵ��
		Digester d = new Digester();
		// ����AddressBookʵ����������ѹ��ջ����
		// �����Ϳ���ʹ�����ķ�����ʵ�ֹ�����
		AddressBook book = new AddressBook();
		d.push(book);
		// ���ӹ���
		addRules(d);
		
		try {
			java.io.File srcfile = new java.io.File(filename);
			// ���������xml�ļ�
			d.parse(srcfile);
		} catch (java.io.IOException ioe) {
			System.out.println("Error reading input file:" + ioe.getMessage());
			System.exit(-1);
		} catch (org.xml.sax.SAXException se) {
			System.out.println("Error parsing input file:" + se.getMessage());
			System.exit(-1);
		}

		// ���������ĵ�ַ���ݴ�ӡ����
		book.print();
	}

	private static void addRules(Digester d) {
		// ������<person>ʱ��������Person��һ��ʵ����������ѹ��ջ��
		d.addObjectCreate("address-book/person", Person.class);

		// ��<person>��ǩ������(attribute)��ջ��Person����������(property)���÷������ݸ��Ե����ֽ���ӳ�䣬 (���磬����ǩ����id���������÷���setId����ӳ�䣬����ǩ����category���������÷���setCategory����ӳ��)��Ȼ�����Ե�ֵ���������ݸ�ִ����Ӧ�ķ�����
		// ���ĳ��ǩ����û��ͨ�������ҵ���Ӧ���������÷�������˱�ǩ���Ա�����(��example.xml�е�һ��<person>��try����)��
		
		// Oliver �� 2008-5-24 ����05:33:24 ����ע��:
		// �������ýڵ�����
		d.addSetProperties("address-book/person");

		// ���õڶ�ջ������(AddressBookʵ��)��addPerson��������ջ����(Personʵ��)�Ķ���Ϊ����
		d.addSetNext("address-book/person", "addPerson");

		// ������<person>����Ԫ��<name>ʱ������ջ������(Personʵ��)��setName������
		// �˴�addCallMethod�����ĵ�һ�����ǹ��򣬵ڶ��������Ƿ��������֣��������ǲ���������(Ϊ0ʱ����ʾֻ��һ���������Ҳ�����ֵ��Ԫ�ص�����)
		d.addCallMethod("address-book/person/name", "setName", 0);

		// ������<person>����Ԫ��<email>ʱ������ջ������(Personʵ��)��addEmail����,addEmail ����������������
		// ȡֵ�ֱ�����<email>������type��ֵ��<email>��������ݡ�
		// �˴�addCallParam�����ĵ�һ�����ǹ��򣬵ڶ���������ָ�������÷���(addEmail)��������ţ��������ǲ���Ϊ�ַ���ʱָ���Ե�����)
		d.addCallMethod("address-book/person/email", "addEmail", 2);
		d.addCallParam("address-book/person/email", 0, "type"); // <email>�ڵ������type��ֵ��Ϊ����
		d.addCallParam("address-book/person/email", 1); // <email>�����������Ϊ�����ĵڶ�������
	}

	private static void usage() {
		System.out.println("Usage: java Example1 example.xml");
	}
}

// ���:

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