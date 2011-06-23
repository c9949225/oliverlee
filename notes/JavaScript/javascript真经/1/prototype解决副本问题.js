

function Person(name) {
	this.name = name; //设置对象属性，每个对象各自一份属性数据
};

Person.prototype.SayHello = function() //给Person函数的prototype添加SayHello方法。
{
	alert("Hello, I'm " + this.name);
}

var BillGates = new Person("Bill Gates"); //创建BillGates对象
var SteveJobs = new Person("Steve Jobs"); //创建SteveJobs对象

BillGates.SayHello(); //通过BillGates对象直接调用到SayHello方法
SteveJobs.SayHello(); //通过SteveJobs对象直接调用到SayHello方法

//因为两个对象是共享prototype的SayHello，所以显示：true
alert(BillGates.SayHello == SteveJobs.SayHello);