/*****************************
Array.js ����ԭ��)չ
--------------------------------------
Author : CNLei, ����
E-Mail : CNLei.Y.L@Gmail.com
MySite : http://www.cnlei.com
Update : 2008-5-19
*****************************/
// 目的:为Array类型的对象添加each函数
// 功能:为数组的每一个元素调用传入的函数
// 参数1:一个函数
// 参数2:是否反向 reverse=-1 ����
Array.prototype.each = function(f,r){//(function,reverse) reverse=-1 ����ִ��
	if(r===-1){
		for(var i=this.length-1;i>=0;i--)f(this[i],i);
	}else{
		for(var i=0,l=this.length;i<l;i++)f(this[i],i);
	}
};

// 目的:为Array类型的对象添加indexOf函数
// 功能:找到数组相中用传入的函数检查返回为true的元素,返回序号
// 如果b为false的话,是将元素作为函数对待
Array.prototype.indexOf = function(v,b){//��
    var idx=-1;
	if(b===true && typeof(v)=="function"){//b:true,����vΪ������ʱ,��ʾ����v���д��?���true/false
		for (var i=0,l=this.length;i<l;i++) {
			if(v(this[i])){idx=i; break;} // 如果将本数组元素传入,调用传入的函数,返回为true,就给idx赋值,说明找到了
		}
	} else {
		for (var i=0,l=this.length;i<l;i++) {
			if(this[i]===v){idx=i; break;} // 如果本书组元素是函数,则说明找到返回序号
		}
	}
	return idx; // 返回找到的数组项的序号
};

// 目的:为Array类型的对象添加lastIndexOf函数
// 功能:找到数组相中用传入的函数检查返回为true的最后一个元素,返回序号
// 就是按照indexOf的方式倒着遍历了一下
Array.prototype.lastIndexOf = function(v,b){//��
    var idx=-1;
	if(b===true && typeof(v)=="function"){//b:true,����vΪ������ʱ,��ʾ����v���д��?���true/false
		for (var i=this.length-1;i>=0;i--) {
			if(v(this[i])){idx=i; break;}
		}
	} else {
		for (var i=this.length-1;i>=0;i--) {
			if(this[i]===v){idx=i; break;}
		}
	}
	return idx;
};

// 目的:为Array类型的对象添加remove函数
// 功能:删除所有满足传入函数的元素
Array.prototype.remove	= function(f){ 
	var ME=this;
	if(typeof(f)=="function"){
		ME.each(function(s,i){
			if(f(s,i))ME.splice(i,1); // 从i开始删除1个元素
		},-1);
	}
	return ME;
};