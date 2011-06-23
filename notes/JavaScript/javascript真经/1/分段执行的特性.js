/*
 * 在同一段程序的分析执行中，定义式的函数语句会被提取出来优先执行。
 */
function myfunc() {
	alert("hello");
};
myfunc(); //这里调用myfunc，输出yeah而不是hello

function myfunc() {
	alert("yeah");
};
myfunc(); //这里调用myfunc，当然输出yeah

//如果把这个JavaScript代码分成两段，例如将它们写在一个html中，
//并用<script/>标签将其分成这样的两块：

function myfunc() {
	alert("hello");
};
myfunc(); //这里调用myfunc，输出hello

function myfunc() {
	alert("yeah");
};
myfunc(); //这里调用myfunc，输出yeah

//这时，输出才是各自按顺序来的，也证明了JavaScript的确是一段段地执行的。
