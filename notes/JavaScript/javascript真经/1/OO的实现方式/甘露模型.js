var object = //定义小写的object基本类，用于实现最基础的方法等
{
	isA : function(aType) //一个判断类与类之间以及对象与类之间关系的基础方法
	{
		var self = this;
		while (self) {
			if (self == aType)
				return true;
			// Type属性？
			self = self.Type;
		}
		;
		return false;
	}
};

function Class(aBaseClass, aClassDefine) //创建类的函数，用于声明类及继承关系
{
	function class_() //创建类的临时函数壳
	{
		this.Type = aBaseClass; //我们给每一个类约定一个Type属性，引用其继承的类
		for ( var member in aClassDefine)
			this[member] = aClassDefine[member]; //复制类的全部定义到当前创建的类
	}
	;
	class_.prototype = aBaseClass;
	return new class_();
};

function New(aClass, aParams) //创建对象的函数，用于任意类的对象创建
{
	function new_() //创建对象的临时函数壳
	{
		this.Type = aClass; //我们也给每一个对象约定一个Type属性，据此可以访问到对象所属的类
		if (aClass.Create)
			aClass.Create.apply(this, aParams); //我们约定所有类的构造函数都叫Create，这和DELPHI比较相似
	}
	;
	new_.prototype = aClass;
	return new new_();
};

//语法甘露的应用效果：    
var Person = Class(object, //派生至object基本类
{
	Create : function(name, age) {
		this.name = name;
		this.age = age;
	},
	SayHello : function() {
		alert("Hello, I'm " + this.name + ", " + this.age + " years old.");
	}
});

var Employee = Class(Person, //派生至Person类，是不是和一般对象语言很相似？
{
	Create : function(name, age, salary) {
		Person.Create.call(this, name, age); //调用基类的构造函数
		this.salary = salary;
	},
	ShowMeTheMoney : function() {
		alert(this.name + " $" + this.salary);
	}
});

var BillGates = New(Person, [ "Bill Gates", 53 ]);
var SteveJobs = New(Employee, [ "Steve Jobs", 53, 1234 ]);
BillGates.SayHello();
SteveJobs.SayHello();
SteveJobs.ShowMeTheMoney();

var LittleBill = New(BillGates.Type, [ "Little Bill", 6 ]); //根据BillGate的类型创建LittleBill
LittleBill.SayHello();

alert(BillGates.isA(Person)); //true
alert(BillGates.isA(Employee)); //false
alert(SteveJobs.isA(Person)); //true
alert(Person.isA(Employee)); //false
alert(Employee.isA(Person)); //true

