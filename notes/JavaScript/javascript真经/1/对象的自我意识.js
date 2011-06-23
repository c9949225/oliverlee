

/*  JavaScript的this却并不一定！this可能是我，也可能是你，可能是他，反正是我中有你，你中有我，
 *  这就不能用原来的那个“自我”来理解 JavaScript这个this的含义了
 *
 *  在JavaScript函数中，你只能把this看成当前要服务的“这个”对象。
 *  this是一个特殊的内置参数，根据this参数，您可以访问到“这个” 对象的属性和方法，
 *  但却不能给this参数赋值。
 *  
 *   JavaScript提供了传递this参数的多种形式和手段，其中，
 *   象BillGates.WhoAmI()和SteveJobs.WhoAmI()这种形式，是传递this参数最正规的形式，
 *   此时的this就是函数所属的对象本身。
 *   
 *   
 *
 **/

function WhoAmI() //定义一个函数WhoAmI
{
	alert("I'm " + this.name + " of " + typeof (this));
};

//此时是this当前这段代码的全局对象，
//在浏览器中就是window对象，其name属性为空字符串。
//输出：I'm of object
WhoAmI();  

var BillGates = {name: "Bill Gates"};
BillGates.WhoAmI = WhoAmI;  //将函数WhoAmI作为BillGates的方法。
BillGates.WhoAmI();         //此时的this是BillGates。输出：I'm Bill Gates of object

var SteveJobs = {name: "Steve Jobs"};
SteveJobs.WhoAmI = WhoAmI;  //将函数WhoAmI作为SteveJobs的方法。
SteveJobs.WhoAmI();         //此时的this是SteveJobs。输出：I'm Steve Jobs of object

WhoAmI.call(BillGates); //直接将BillGates作为this，调用WhoAmI。输出：I'm Bill Gates of object
WhoAmI.call(SteveJobs); //直接将SteveJobs作为this，调用WhoAmI。输出：I'm Steve Jobs of object

//this只是在任意对象和function元素结合时的一个概念，是种结合比起一般对象语言的默认结合更加灵活`，显得更加超然和洒脱。
//将SteveJobs作为this，却调用BillGates的WhoAmI方法。输出：I'm Steve Jobs of object
BillGates.WhoAmI.call(SteveJobs);
//将BillGates作为this，却调用SteveJobs的WhoAmI方法。输出：I'm Bill Gates of object
SteveJobs.WhoAmI.call(BillGates);

WhoAmI.WhoAmI = WhoAmI;     //将WhoAmI函数设置为自身的方法。
WhoAmI.name = "WhoAmI";
WhoAmI.WhoAmI();            //此时的this是WhoAmI函数自己。输出：I'm WhoAmI of function

//临时创建一个匿名对象并设置属性后调用WhoAmI方法。输出：I'm nobody of object
({name: "nobody", WhoAmI: WhoAmI}).WhoAmI();   



