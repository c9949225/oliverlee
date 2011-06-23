
/*JSON的形式就是用大括“{}”号包括起来的项目列表，每一个项目间并用逗号“,”分隔，
 * 而项目就是用冒号“:”分隔的属性名和属性值。这是典型的字典表示形式，
 * 也再次表明了 JavaScript里的对象就是字典结构。*/


/*当需要将这个JSON字符串变成一个JavaScript对象时，只需要使用eval函数这个强大的数码转换引擎，
 * 就立即能得到一个 JavaScript内存对象。*/



//  创建一个没有任何属性的对象：
var o = {};

//创建一个对象并设置属性及初始值：
var person = {name: "Angel", age: 18, married: false};

//创建一个对象并设置属性和方法：
var speaker = {text: "Hello World", say: function(){alert(this.text)}};

//创建一个更复杂的对象，嵌套其他对象和对象数组等：
var company =
{
    name: "Microsoft",
    product: "softwares",
    chairman: {name: "Bill Gates", age: 53, Married: true},
    employees: [{name: "Angel", age: 26, Married: false}, {name: "Hanson", age: 32, Marred: true}],
    readme: function() {document.write(this.name + " product " + this.product);}
};






