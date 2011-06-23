var Person = //定义一个对象来作为原型类
{
	Create : function(name, age) //这个当构造函数
	{
		this.name = name;
		this.age = age;
	},
	SayHello : function() //定义方法
	{
		alert("Hello, I'm " + this.name);
	},
	HowOld : function() //定义方法
	{
		alert(this.name + " is " + this.age + " years old.");
	}
};


//如果可以用某种形式来创建对象，并将对象的内置的原型设置为上面这个“类”对象，
//不就相当于创建该类的对象了吗？
//但遗憾的是，我们几乎不能访问到对象内置的原型属性！尽管有些浏览器可以访问到对象
//的内置原型，但这样做的话就只能限定了用户必须使用那种浏览器。这也几乎不可行。

//那么，我们可不可以通过一个函数对象来做媒介，
//利用该函数对象的prototype属性来中转这个原型，并用new操作符传递给新建的对象呢？

function anyfunc(){};           //定义一个函数躯壳
anyfunc.prototype = Person;     //将原型对象放到中转站prototype
var BillGates = new anyfunc();  //新建对象的内置原型将是我们期望的原型对象

//不过，这个anyfunc函数只是一个躯壳，在使用过这个躯壳之后它就成了多余的东西了，
//而且这和直接使用构造函数来创建对象也没啥不同，有点不爽。

//如果我们将这些代码写成一个通用函数，而那个函数躯壳也就成了函数内的函数，
//这个内部函数不就可以在外层函数退出作用域后自动消亡吗？

//通用创建函数
function New(aClass, aParams) 
{
	function new_() //定义临时的中转函数壳
	{
		//调用原型中定义的的构造函数，中转构造逻辑及构造参数
		aClass.Create.apply(this, aParams); 
	}
	;
	new_.prototype = aClass; //准备中转原型对象
	return new new_(); //返回建立最终建立的对象
	// new_函数会随外层New函数退出作用域后自动消亡
};

var Person = //定义的类
{
	// 构造函数
	Create : function(name, age) {
		this.name = name;
		this.age = age;
	},
	SayHello : function() {
		alert("Hello, I'm " + this.name);
	},
	HowOld : function() {
		alert(this.name + " is " + this.age + " years old.");
	}
};

//调用通用函数创建对象，并以数组形式传递构造参数
var BillGates = New(Person, [ "Bill Gates", 53 ]);
BillGates.SayHello();
BillGates.HowOld();

alert(BillGates.constructor == Object); //输出：true

//这里的通用函数New()就是一个“语法甘露”！这个语法甘露不但中转了原型对象，还中转了构造函数逻辑及构造参数。
