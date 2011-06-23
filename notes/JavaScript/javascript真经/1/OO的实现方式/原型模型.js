//原型模型需要一个构造函数来定义对象的成员，而方法却依附在该构造函数的原型上。

//原型类模型虽然不能模拟真正的私有变量，而且也要分两部分来定义类，显得不怎么“优雅”。
//不过，对象间的方法是共享的，不会遇到垃圾回收问题，而且性能优于“闭包”模型。
//正所谓“有失必有得”嘛。

// 在原型模型中，为了实现类继承，必须首先将子类构造函数的prototype设置为一个父类的对象实例。
// 创建这个父类对象实例的目的就是为了构成原型链，以起到共享上层原型方法作用。
 
//定义构造函数
function Person(name) {
	this.name = name; //在构造函数中定义成员
};

//方法定义到构造函数的prototype上
Person.prototype.SayHello = function() {
	alert("Hello, I'm " + this.name);
};

//子类构造函数
function Employee(name, salary) {
	Person.call(this, name); //调用上层构造函数
	this.salary = salary; //扩展的成员
};

//子类构造函数首先需要用上层构造函数来建立prototype对象，实现继承的概念
Employee.prototype = new Person();

//只需要其prototype的方法，此对象的成员没有任何意义！

//子类方法也定义到构造函数之上
Employee.prototype.ShowMeTheMoney = function() {
	alert(this.name + " $" + this.salary);
};

var BillGates = new Person("Bill Gates");
BillGates.SayHello();

var SteveJobs = new Employee("Steve Jobs", 1234);
SteveJobs.SayHello();
SteveJobs.ShowMeTheMoney();
