/*****************************
lTREE.js	 ���޼�CSS TreeMenu Ver2.0
--------------------------------------
Author : CNLei, ����
E-Mail : CNLei.Y.L@Gmail.com
MySite : http://www.cnlei.com
Passed : IE6.0+, FF1.0+, Opera8.5+, Safari 3.0+
Update : 2008-5-19
*****************************/
// 定义一个lTREE类,初始话默认属性
var lTREE = function(){
	var ME=this;
	this.author="CN.LEI <cnlei.y.l@gmail.com>";
	this.onclick	=null;
	this.config={
		path	: "ul li",
		classClosed: "Closed",
		func	: null,
		mode	: false,
		openAll:false
	};
	//if(lDOM.isIE)document.execCommand("BackgroundImageCache", false, true);
};

// 为lTREE类添加click,set,setAll,init方法
lTREE.prototype	= {
	click	 : function(o){
		o=o.parentNode;
		var a=this.config,c=a.classClosed,b=lDOM.hasClass(o,c);
		if(!b){
			lDOM.addClass(o,c);
		} else {
			lDOM.delClass(o,c);
		}
		if(typeof(this.onclick)=="function"){//�����Զ���click����
			//o:��ǰ���Ԫ��
			//b:��ǰ����Ƿ�Ϊ��״̬
			this.onclick(o,b);
		}
		return b;
	},
	set	: function(s){
		var ME=this,o=document.createElement("button"); // 创建一个按钮
		s.insertBefore(o,s.firstChild); // 设置到指定的子元素的前面
		o.onfocus=function(){
			ME.click(this);
			this.blur();
			return false;
		};
		o =null;
		return true;
	},
	setAll	: function(n){
		var c=this.config.classClosed;
		//var t=new Date();
		if(n>0){
			this.item.each(function(s){
				lDOM.delClass(s,c);
			});
		} else {
			this.item.each(function(s){
				lDOM.addClass(s,c);
			});
		}
		//alert("��ʱ:"+(new Date()-t)+"\n����:"+this.item.length+"����");
	},
	init	: function(c){
		if (!lDOM.$(c.id)) return; //若不能取出指定id的div容器的控件,则返回
		var o=this.config;
		for(var x in c)o[x]=c[x];//将c传入的配置都传给本lTree对象
		var ME=this,cn="s";
		var f=o.func; 
		// 如果新建的对象func属性还未定义,则定义为一个调用本对象set方法的函数
		// 为x前面添加一个button
		f=typeof(f)=="function"?f:function(x){ME.set(x);return true;};
		// o.path 指要操作的html标签 "dl dd"
		// f 指要调用的方法,默认为lTree的set方法
		// lDOM.${o.id}是指包含标签内容的div容器
		// o.mode 指的是模式,默认是false
		this.item=lDOM.each(o.path,lDOM.$(o.id),f,o.mode); // 调用lDOM的each方法
		// this.item保存了所有出现的DD标签,DD标签是有父子关系的
		if(o.openAll)this.setAll(1); 
	}
};

// 所以灵魂是lDOM的each方法循环调用lTree的set方法
