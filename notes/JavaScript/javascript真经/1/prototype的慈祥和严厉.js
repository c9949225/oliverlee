//任何一个孩子虽然可以我行我素，但却不能动原型对象既有的财产，
//因为那可能会影响到其他孩子的利益。

function Person(name) {
	this.name = name;
};

Person.prototype.company = "Microsoft"; //原型的属性

Person.prototype.SayHello = function() //原型的方法
{
	alert("Hello, I'm " + this.name + " of " + this.company);
};

//由于继承了原型的东西，规规矩矩输出：Hello, I'm Bill Gates
var BillGates = new Person("Bill Gates");
BillGates.SayHello(); 


var SteveJobs = new Person("Steve Jobs");
//设置自己的company属性，掩盖了原型的company属性
SteveJobs.company = "Apple"; 
//实现了自己的SayHello方法，掩盖了原型的SayHello方法
SteveJobs.SayHello = function()
{
	alert("Hi, " + this.name + " like " + this.company + ", ha ha ha ");
};

SteveJobs.SayHello(); //都是自己覆盖的属性和方法，输出：Hi, Steve Jobs like Apple, ha ha ha 
BillGates.SayHello(); //SteveJobs的覆盖没有影响原型对象，BillGates还是按老样子输出
