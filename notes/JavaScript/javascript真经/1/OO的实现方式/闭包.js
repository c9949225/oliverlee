/* 所谓的“闭包”，就是在构造函数体内定义另外的函数作为目标对象的方法函数，
 而这个对象的方法函数反过来引用外层函数体中的临时变量。
 这使得只要目标对象在生存期内始终能保持其方法，
 就能间接保持原构造函数体当时用到的临时变量值。
 尽管最开始的构造函数调用已经结束，临时变量的名称也都消失了，
 但在目标对象的方法内却始终能引用到该变量的值，而且该值只能通这种方法来访问。*/
 
/*给每一个对象设置一份方法是一种很大的浪费。还有，“闭包”这种间接保持变量值的机制，
往往会给JavaSript的垃圾回收器制造难题。特别是遇到对象间复杂的循环引用时，
垃圾回收的判断逻辑非常复杂。无独有偶，IE浏览器早期版本确实存在JavaSript垃圾回收
方面的内存泄漏问题。再加上“闭包”模型在性能测试方面的表现不佳，
微软最终放弃了“闭包”模型，而改用“原型”模型。正所谓“有得必有失”嘛。*/

function Person(firstName, lastName, age) {
	//私有变量：
	var _firstName = firstName;
	var _lastName = lastName;

	//公共变量:
	this.age = age;

	//方法：
	this.getName = function() {
		// 这里持有私有变量
		return (firstName + " " + lastName);
	};
	
	this.SayHello = function() {
		alert("Hello, I'm " + firstName + " " + lastName);
	};
};

var BillGates = new Person("Bill", "Gates", 53);
var SteveJobs = new Person("Steve", "Jobs", 53);

BillGates.SayHello();
SteveJobs.SayHello();
alert(BillGates.getName() + " " + BillGates.age);
//特别是“闭包”机制可以模拟对私有成员的保护机制，做得非常漂亮。
alert(BillGates.firstName); //这里不能访问到私有变量


