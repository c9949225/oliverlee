/*****************************
lDOM.js	DOM������
--------------------------------------
Author : CNLei, ����
E-Mail : CNLei.Y.L@Gmail.com
MySite : http://www.cnlei.com
Update : 2008-5-21
*****************************/
var lDOM ={
	// 用来检测浏览器的属性
	BV	 :(function(){
		var u="indexOf",
			n=navigator,
			a= n.userAgent.toLowerCase(),
			b= (document.getElementById?true:false),
			c= ((a[u]("msie")!=-1) && (a[u]("opera")==-1) && (a[u]("omniweb")==-1)),
			d= b && (n.appName=="Netscape"),
			e= a[u]("opera")!= -1,
			f = a[u]("gecko") != -1;
			return {AGT:a,isW3C:b,isIE:c,isFF:d,isNS6:d,isOP:e,isGecko:f};
		})(),
	// 利用id提取控件,否则返回原字符串
	$	: function(s){
		var o = typeof(s)=="string"?document.getElementById(s):s;
		try{return o;} finally {o=null;}
	},
	// 返回指定的控件是否使用指定的css class
	hasClass: function(e,c){
		if (!(e = this.$(e))) return false;
		e = e.className || " ";
		e = " " + e + " ";
		c = " " + c + " ";
		return (e.indexOf(c)!=-1);
	},
	// 为指定控件添加指定的css class
	addClass: function(e,c){
		if (!(e = this.$(e))) return;
		if (this.hasClass(e,c)){return;}
		e.className=e.className+" "+c;
	},
	// 为指定控件删除指定的css class
	delClass: function(e,c){//(element,className)
		if(this.hasClass(e,c)){
			e = this.$(e);
			var a=e.className.split(" ");
			a.remove(function(s){
				return s==c;
			});
			e.className=a.join(" ");
		}
	},
	// each方法最终会返回一个数组,f函数将为dd标签中的a标签前添加一个按钮
	each	: function(a,b,f,t){//("dl dd",DIV:"lTREEMenuDEMO",默认为lTree的set函数,tagPathType:最初未设置)
		if (!(b = this.$(b))) b=document.body; // 如果不能获取,默认使用body
		if(!b.length)b=[b];// 把容器div(b)换成一个数组
		
		// a=(function(p){...})(a);就是说a等于,定义一个函数,并将a传入这个函数,对a处理后的结果,赋值给a
		a=(function(p){ // 这里p其实就是参数a
			if(typeof(p)!="string") return []; 
			//p=p.tirm();
			p=p.replace(/\s+/g," ").split(" "); // 将dl dd以空格分隔为数组p
			var r=[],ns=0,ne=0;
			/**
			 * p是一个数组[dl][dd]
			 * 为p的每一个元素调用传入的函数
			 * s将会是p中的一个元素,i表示该元素迭代的序号
			 */
			// ===============================================================
			p.each(function(s,i){// ��ʽ������
				var n=s.indexOf(".");
				if(n>-1){ // 如果s包含.
					if(n==0)s="*"+s; // 如果.s的话就变成*.s
				} else {
					s=s+".*";  
				}
				s=s.split("."); // 以.做为分隔
				var t=s[0].toUpperCase(),c=s[1]; // DL *
				p[i]=[t,c]; // p最后变为为[DL,*][DD,*]
			});
			// ===============================================================
			// ===============================================================
			// 再反向调用一次传入的函数
			// p已经变成[DL,*][DD,*]
			p.each(function(s,i){
				// r是前面定义的数组 
				var m=r.indexOf(function(x,k){
						return s[0]==x[0];  // DL
					},true);// 1, 首先会没找到,因为r最开始是空的
					// 3, 接着查找DL,indexOf里传入的是DL确实是最开始的元素DL
					if(m>-1){
						r[m][2]+=1; 
						r[m][1].push(s[1]);
					} else { // 2, 然后,放入数组[DL,[*],1]
						r.push([s[0],[s[1]],1]);
					}
			},-1);
			// ===============================================================
			// r到这里为2个元素的数组,内容为[DD,*,1],[DL,*,1]
			r.reverse(); 
			// 颠倒数组r的顺序后:[DL,*,1],[DD,*,1]
			// a的内容仍为"dl dd"
			a=null;
			// 依次遍历r的每一个元素,为之调用传入的方法
			// s为r中元素
			// i为序号
			// ===============================================================
			r.each(function(s,i){//
			// ne为返回序号
			// p为最开始分隔出来的数组����ͳ��
				ne=p.lastIndexOf(function(x){
					return x[0]==s[0];
				},true)+1;
				//alert("ԭ:"+s[2]);
				s[2]=0;
				p.slice(ns,ne).each(function(y){//�ֶν�ȡ
					if(y[0]==s[0])s[2]+=1;
				});
				//alert("��:"+s[2]);
				ns=ne;
			});
			
		// ===============================================================
			r.each(function(s){//���»�ȡ��ЧclassName
				s[1]=s[1].slice(0,s[2]);
			});
			
			// r 到这里的内容:[DL,*,1],[DD,*,1]
			return r;
			})(a);
			// a 变成了处理后的数组
			var ME=this,r=[];
// ===============================================================
			function AR(a,f,d){//������ arraryRun(array,function,direction);
				if(d===-1){
					for(var i=a.length-1; i>=0;i--){
						f(a[i],i); // 为DD标签添加button
					}
				} else {
					for(var i=0,l=a.length; i<l;i++){
						f(a[i],i);
					}
				}
			}

			//��⸸�����ĺϷ��� 总共有3个DL,该方法会被调用3次ֻ�е�ǰ�㼶���Ϸ�
			function $N(sp,t,p){//isOKPNode(subNode,tagInfo,pNode)
				var o=sp,tn=t[0],cls=t[1],cnt=t[2],n=0,b=false;
				// tn: DL
//				alert("cls:"+cls); *
//				alert("cnt:"+cnt); 1
				if(tn=="")tn=o.tagName;
				while(o!=p && n<cnt){
					// 只循环了1次,可能是因为创建数组的时候就设置了1
					if(o.tagName==tn){
						if(cls[n]!="*"){
							if(ME.hasClass(o,cls[n]))n+=1;
						} else {
							// 由于cls[n]=*进入到这里
							n+=1;
						}
					}
					o=o.parentNode;
				}
				if(n==cnt){
					b=true;
					while(o!=p && b){
						// tn永远是DL
//						alert("o.tagName:"+o.tagName+"tn:"+tn);
						if(o.tagName==tn){
							b=false;
						}
						o=o.parentNode;
					}
				}
//				alert(b+":"+tn+":"+cnt+":"+n);
				return b;
			}
			//������ս��ĺϷ���,��ȴ��ڵ��ڵ�ǰ�㼶�Ľ�㶼�Ϸ�,���������ﶼ�Ϸ�
			function $C(sp,t,p){//isOKPNode(subNode,tagInfo,pNode)
				var o=sp,tn=t[0],cls=t[1],cnt=t[2],n=0;//,b=false;
				if(tn=="*")tn=o.tagName;
				while(o!=p && n<cnt){
					if(o.tagName==tn){
						if(cls[n]!="*"){
							if(ME.hasClass(o,cls[n])){
								n+=1;
							}
						} else {
								n+=1;
						}
					}
					o=o.parentNode;
				}
				//b=(n==cnt);
				//return b;
				return n==cnt;
			}

			// DL,*,1
			// 包含容器对象lTREEMenuDEMO的数组
			// $N
			function $F(t,p,f){//choseNodes(tagInfo,arrParent,isLastLevel,callFunc)
				var tn=t[0],R=[]; // tn = DL
				AR(p,function(o,i){
					// 得到容器下所有的DL控件
					var nodes=o.getElementsByTagName(tn);//��ǰ����µ������ӽ��
					AR(nodes,function(c,j){//�����ӽ��
							if(f(c,t,o)){//
								R.push(c);  
							}
					});
				});
//				alert(tn+":"+R.length);
				return R; // 把所有的DL标签都存入了数组R
			}

			//ɸѡ���ղ���
			function $L(t,p,f,b){//choseNodes(tagInfo,arrParent,callFunc,choseMode)
				var cm=(b===true?$N:$C);
				if(typeof(f)=="function"){
					var tn=t[0],R=[];
					AR(p,function(o,i){
						var nodes=o.getElementsByTagName(tn);
						AR(nodes,function(c,j){
								if(cm(c,t,o)){
									if(f(c,R.length)){
										R.push(c);
									}
								}
						});
					});
					return R; // 将所有的DD标签存入了R
				} else {
					return $F(t,p,cm);
				}
			}
			//���϶�����ɸѡ
			function $A(t,p,f,m){//choseAllNodes(tagAllInfo,arrParent,callFunc,choseMode)
//				alert(t); // [DL,*,1],[DD,*,1]
//				alert(p); // 长度为1的数组,包含容器div的对象
//				alert(f); // 函数
//				alert(m); // false
				 
				var l=t.length-1;
				AR(t,function(s,i){
					if(i>=l){
//						s=DD,*,1
						p=$L(s,p,f,m);
					} else {
//						s=DL,*,1
						p=$F(s,p,$N);
					}
				});
				return p; // 如果是$F就是所有DL标签元素,如果是$L就是所有的DD标签
				// 如果是$L就是所以的
			}
// ===============================================================
			r=$A(a,b,f,t); // b是长度为1的数组,包含了容器div对象
			// 最终each函数返回了2个DD标签控件的数组
			// 一个父DD嵌套了一个子DD
			return r;
	},
	// 查找,指定标签路径,父元素,属性,标签路径类型
	find	: function(a,p,A,t){//(tagPath,parent,attr,tagPathType)
		//��5��: this.each(),this.hasAttr()
		var ME=this,R=[];
		if(typeof(A)=="object"){// 如果属性是对象
			var f	= function(x,i){
				return ME.hasAttr(x,A);
			};
			R=ME.each(a,p,f,t);
		} else {
			R=ME.each(a,p,null,t);
		}
		return R;//��ʱ����
	},
	fixAttr:function (){
		return{
			"for": "htmlFor",
			"class": "className",
			"float": this.BV.isIE ? "styleFloat" : "cssFloat",
			cssFloat: this.BV.isIE ? "styleFloat" : "cssFloat",
			innerHTML: "innerHTML",
			className: "className",
			value: "value",
			disabled: "disabled",
			checked: "checked",
			readonly: "readOnly",
			selected: "selected",
			tagName	: "tagName"//������Firefox����getAttribute()�����޷�ȡ��tagName
		};
	},
	// 判断传入的控件是否包含指定属性
	hasAttr	: function(o,a){//hasAttribs(object,attribs)
		//��5��:this.fixAttr()
		var b = true;
		var fix = this.fixAttr(),r;
		for (var x in a){
			if (fix[x]){
				r=o[fix[x]];
			} else{
				//r=o[x];
				r=o.getAttribute(x);
			}
			if(typeof(a[x])=="function"){
				if(!a[x](r)){b=false;break;}
			} else {
				if(r!=a[x]){b=false;break;}
			}
		}
		return b;
	},
	// 设置控件e的属性值
	setAttr : function (e,a){//setAttribs(element,attribs)//�򻯰�
		for (var x in a){
			e[x] = a[x];
		}
	},
	// 为指定控件设置style属性
	setStyle : function (e,s){//setStyle(element,styles)
		this.setAttr(e["style"],s);
	},
	// 创建一个控件,放置在父元素的第一个子元素的前面,添加指定的属性和class,返回该控件
	CE	: function (t,a,s,p,w){//createElement(tagName,attribs,styles,parentNode,where)
		//w = w===0?0:-1;
		//where:0�����һ��λ��, -1�������һ��λ��
		var el = document.createElement(t);
		if (p)w===0?p.insertBefore(el,p.firstChild):p.appendChild(el);
		if (a) this.setAttr(el, a);
		if (s) this.setStyle(el, s);
		try{return el;} finally {el=null;}
	}
};