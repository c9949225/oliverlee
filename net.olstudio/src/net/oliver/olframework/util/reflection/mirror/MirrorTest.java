package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

public class MirrorTest {

	public void test()
	{
		// �������ƻ�ȡĳ�����Class����
		Class<?> clazz = new Mirror().reflectClass("net.oliver.olframework.util.reflection.mirror.Bean");
//		Field f = new Mirror().on(clazz).reflect().field("field1");
//		System.out.println(f.getType().getName());
		List<Method> lm = new Mirror().on(clazz).reflectAll().methodsMatching(new MethodNameMatcher("serverice"));
		
		Object[] objs = ((Method)lm.get(0)).getParameterTypes();
		
		for(int i=0;i<objs.length;i++)
		{
			System.out.println(objs[i].toString().substring(objs[i].toString().indexOf(" ")));
		}
	/*	List<Field> l = new Mirror().on(clazz).reflectAll().fields();
		l = new Mirror().on(clazz).reflectAll().fieldsMatching(new Matcher<Field>(){

			public boolean accepts(Field arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		// ���þ�̬����
		new Mirror().on(clazz).set().field("fieldName").withValue("");
		// ����ʵ������
		new Mirror().on(new Student()).set().field("name").withValue("cenjuny");
		
		// ����Fieldֵ
		new Mirror().on(clazz).set().field(f).withValue("");
		
		Object value = new Mirror().on(clazz).get().field("fieldName");
		value = new Mirror().on(new Object()).get().field("fieldName");
		value = new Mirror().on(clazz).get().field(f);
		
		// ********����********
		// ��ȡ��̬Method����
		Method m = new Mirror().on(clazz).reflect().method("methodName").withoutArgs();
		// ���ݲ���
		m = new Mirror().on(clazz).reflect().method("methodName").withArgs(String.class, Object.class);
		
		// ��ȡĳ��������з��������б�
		List<Method> lm = new Mirror().on(clazz).reflectAll().methods();
		// ��ȡĳ���������getter
		lm = new Mirror().on(clazz).reflectAll().getters();
		// ��ȡĳ���������setter
		lm = new Mirror().on(clazz).reflectAll().setters();
		// ����ƥ��������ѯ�����б�
		lm = new Mirror().on(clazz).reflectAll().methodsMatching(new Matcher<Method>(){

			public boolean accepts(Method arg0) {
				return false;
			}
			
		});
		
		// ���þ�̬����
		Object returnValue = new Mirror().on(clazz).invoke().method("methodName").withArgs("", "");
		
		// ����ʵ������
		returnValue = new Mirror().on(new Object()).invoke().method("methodName").withoutArgs();
		
		// ����Method����
		returnValue = new Mirror().on(new Object()).invoke().method(m) .withArgs("", "");
		
		// ����Setter,ΪField��ֵ
		new Mirror().on(new Object()).invoke().setterFor(f).withValue("");
		new Mirror().on(new Object()).invoke().setterFor("fieldName").withValue("");
		
		// ΪField����Getter
	    returnValue = new Mirror().on(new Object()).invoke().getterFor(f);
		returnValue = new Mirror().on(new Object()).invoke().getterFor("fieldName");*/
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		MirrorTest test = new MirrorTest();
		test.test();
	/*	Class<?> c = Mirror.reflectClass("net.vidageek.example.SomeClass");
		// ��ȡָ������
		Field f = Mirror.on(c).reflect().field("fieldName");
		// ��ȡ��������
		List<Field> l = Mirror.on(c).reflectAll().fields();
		
		l = Mirror.on(c).reflectAll().fieldsMatching(new Matcher<Field>(){

			public boolean accepts(Field arg0) {
				return false;
			}
			
		});*/
		
		
		
	}

}
