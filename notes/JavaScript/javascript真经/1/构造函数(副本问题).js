
function Person(name) //带参数的构造函数
{
	this.name = name; //将参数值赋给给this对象的属性
	this.SayHello = function() { //给this对象定义一个SayHello方法。
		alert("Hello, I'm " + this.name);
	}; 
};

function Employee(name, salary) //子构造函数
{
	Person.call(this, name); //将this传给父构造函数
	
	this.salary = salary; //设置一个this的salary属性
	
	this.ShowMeTheMoney = function() {//添加ShowMeTheMoney方法。
		alert(this.name + " $" + this.salary);
	}; 
};

var BillGates = new Person("Bill Gates"); //用Person构造函数创建BillGates对象
var SteveJobs = new Employee("Steve Jobs", 1234); //用Empolyee构造函数创建SteveJobs对象

BillGates.SayHello(); //显示：I'm Bill Gates
SteveJobs.SayHello(); //显示：I'm Steve Jobs
SteveJobs.ShowMeTheMoney(); //显示：Steve Jobs $1234

alert(BillGates.constructor == Person); //显示：true
alert(SteveJobs.constructor == Employee); //显示：true


/*用构造函数操作this对象创建出来的每一个对象，不但具有各自的成员数据，而且还具有各自的方法数据。
换句话说，方法的代码体(体现函数逻辑的数据)在每一个对象中都存在一个副本。
尽管每一个代码副本的逻辑是相同的，但对象们确实是各自保存了一份代码体。*/

// ======================================================================
alert(BillGates.SayHello == SteveJobs.SayHello); //显示：false
//======================================================================