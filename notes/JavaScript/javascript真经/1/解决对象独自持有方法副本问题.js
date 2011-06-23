function SayHello() //先定义一份SayHello函数代码
{
	alert("Hello, I'm " + this.name);
};

function Person(name) //带参数的构造函数
{
	this.name = name; //将参数值赋给给this对象的属性
	this.SayHello = SayHello; //给this对象SayHello方法赋值为前面那份SayHello代码。
};

var BillGates = new Person("Bill Gates"); //创建BillGates对象
var SteveJobs = new Person("Steve Jobs"); //创建SteveJobs对象

alert(BillGates.SayHello == SteveJobs.SayHello); //显示：true
