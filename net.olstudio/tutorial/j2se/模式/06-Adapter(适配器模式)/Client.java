/*
 * 适用性：
 * 
 * 你想使用一个已经存在的类，而他的接口不符合你的需求。
 * 
 * 你想创建一个可以复用的类，该类可以与其他不相关的类或不可预见的类"协同"工作。
 * 
 * 你想使用一些已经已经存在的子类，但是不可能对每一个都进行子类化以匹配他们的接口，
 * 对象适配器可以适配他的父类接口。
 * 
 */
public class Client {
	public static void main(String[] args) {
		long exp = 5;
		Target t = new Adapter();
		String str = " 2 power " + exp + "=";
		str += t.get2Power(exp);
		System.out.println(str);
	}
}

/*
适配器如同一个常见的变压器，也如同电脑的变压器和插线板之间的电源连接线，
他们虽然都是3相的，但是电脑后面的插孔却不能直接插到插线板上。

作者曾经遇到过一个ASP编程的难题，asp不是面向对象的，
但是却可以借鉴适配器模式解决问题。

问题是这样的，在一个产品表(product)中的所有产品都有一个编号，字段名字是bh,
每个编号是唯一的，但却不是主键，表中使用一个自动增长的id作为主键。

在产品的详情页中使用传过来的参数id查询产品，而在另外的一个系统中也有一个同样的表，
需要访问详情页(已经由另外的一个程序员设计好，并且代码晦涩难懂)，

由于字段值是自动增长的，两个表中的主键并不对应（在其中的一个系统中进行删除添加都会引起id的增长），

在具体的实现中，本人在有详情页的系统中添加了一个页面(adapter)，接受传过来的产品编号bh,

然后根据编号查找数据库得到相应产品的驻键id，最后让页面跳转到详情页并传递一个id，

在另外的系统中只要得到产品的编号bh,并把bh作为参数传递到添加的页面(adapter)便可以得到正确的结果。
 
如果觉得写得好，请一定要支持一下，以给我信心和鼓励把剩下的模式例子写出来。
 */