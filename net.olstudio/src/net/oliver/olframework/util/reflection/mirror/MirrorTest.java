package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

public class MirrorTest {

	public void test()
	{
		// 根据名称获取某个类的Class对象
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
		// 设置静态变量
		new Mirror().on(clazz).set().field("fieldName").withValue("");
		// 设置实例变量
		new Mirror().on(new Student()).set().field("name").withValue("cenjuny");
		
		// 设置Field值
		new Mirror().on(clazz).set().field(f).withValue("");
		
		Object value = new Mirror().on(clazz).get().field("fieldName");
		value = new Mirror().on(new Object()).get().field("fieldName");
		value = new Mirror().on(clazz).get().field(f);
		
		// ********方法********
		// 获取静态Method对象
		Method m = new Mirror().on(clazz).reflect().method("methodName").withoutArgs();
		// 根据参数
		m = new Mirror().on(clazz).reflect().method("methodName").withArgs(String.class, Object.class);
		
		// 获取某个类的所有方法对象列表
		List<Method> lm = new Mirror().on(clazz).reflectAll().methods();
		// 获取某个类的所有getter
		lm = new Mirror().on(clazz).reflectAll().getters();
		// 获取某个类的所有setter
		lm = new Mirror().on(clazz).reflectAll().setters();
		// 根据匹配条件查询方法列表
		lm = new Mirror().on(clazz).reflectAll().methodsMatching(new Matcher<Method>(){

			public boolean accepts(Method arg0) {
				return false;
			}
			
		});
		
		// 调用静态方法
		Object returnValue = new Mirror().on(clazz).invoke().method("methodName").withArgs("", "");
		
		// 调用实例方法
		returnValue = new Mirror().on(new Object()).invoke().method("methodName").withoutArgs();
		
		// 调用Method对象
		returnValue = new Mirror().on(new Object()).invoke().method(m) .withArgs("", "");
		
		// 调用Setter,为Field赋值
		new Mirror().on(new Object()).invoke().setterFor(f).withValue("");
		new Mirror().on(new Object()).invoke().setterFor("fieldName").withValue("");
		
		// 为Field调用Getter
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
		// 获取指定属性
		Field f = Mirror.on(c).reflect().field("fieldName");
		// 获取所有属性
		List<Field> l = Mirror.on(c).reflectAll().fields();
		
		l = Mirror.on(c).reflectAll().fieldsMatching(new Matcher<Field>(){

			public boolean accepts(Field arg0) {
				return false;
			}
			
		});*/
		
		
		
	}

}
