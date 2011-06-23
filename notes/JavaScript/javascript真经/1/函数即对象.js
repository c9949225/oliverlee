/*
 * Sing函数被定义后，又给Sing函数动态地增加了author和poem属性。
 * 让我们理解了JavaScript函数就是对象的本质
 * 
 * */
function Sing() {
	with(arguments.callee)
		alert(author + "：" + poem);
};

Sing.author = "李白";
Sing.poem = "汉家秦地月，流影照明妃。一上玉关道，天涯去不归";
Sing();

Sing.author = "李战";
Sing.poem = "日出汉家天，月落阴山前。女儿琵琶怨，已唱三千年";
Sing();
