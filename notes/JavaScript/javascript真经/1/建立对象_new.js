
function MyFunc() {}; //定义一个空函数
var anObj = new MyFunc();  //使用new操作符，借助MyFun函数，就创建了一个对象
 
// 其实，可以把上面的代码改写成这种等价形式：
 
 
 function MyFunc(){};
 var anObj = {};     //创建一个对象
 MyFunc.call(anObj); //将anObj对象作为this指针调用MyFunc函数

