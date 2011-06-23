/*
 * 数组应该算是线性数据结构，线性数据结构一般有一定的规律，适合进行统一的批量迭代操作等，
 * 有点像波。
 * 而对象是离散数据结构，适合描述分散的和个性化的东西，有点像粒子。
 * 因此，我们也可以这样问：JavaScript里的对象到底是波还是粒子？
 * 如果存在对象量子论，那么答案一定是：波粒二象性！
 * */

var anObject = {};  // 新建一个对象

anObject.aProperty = "Property of object";  // 对象的一个属性
anObject.aMethod = function(){alert("Method of object")}; // 对象的一个方法
    
// 主要看下面：
alert(anObject["aProperty"]);   // 可以将对象当数组以属性名作为下标来访问属性
anObject["aMethod"]();          // 可以将对象当数组以方法名作为下标来调用方法

for( var s in anObject)           // 遍历对象的所有属性和方法进行迭代化处理
        alert(s + " is a " + typeof(anObject[s]));

//    同样对于function类型的对象也是一样：

var aFunction = function() {};  // 一个函数，也是一个对象

aFunction.aProperty = "Property of function";  // 函数的一个属性
aFunction.aMethod = function(){alert("Method of function")}; // 函数的一个方法
    
// 主要看下面：
alert(aFunction["aProperty"]);   // 可以将函数当数组以属性名作为下标来访问属性
aFunction["aMethod"]();          // 可以将函数当数组以方法名作为下标来调用方法
    
for( var s in aFunction)           // 遍历函数的所有属性和方法进行迭代化处理
        alert(s + " is a " + typeof(aFunction[s]));
