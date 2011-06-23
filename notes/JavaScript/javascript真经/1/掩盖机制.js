//比静态对象语言更神奇的是，我们可以随时给原型对象动态添加新的属性和方法，
//从而动态地扩展基类的功能特性。这在静态对象语言中是很难想象的

function Person(name) {
	this.name = name;
};

Person.prototype.SayHello = function() //建立对象前定义的方法
{
	alert("Hello, I'm " + this.name);
};

var BillGates = new Person("Bill Gates"); //建立对象

BillGates.SayHello();

//建立对象后，再动态扩展原型的方法，注意是在对象建立后，再修改类
Person.prototype.Retire = function() 
{
	alert("Poor " + this.name + ", bye bye!");
};

BillGates.Retire(); //动态扩展的方法即可被先前建立的对象立即调用
