var object = //����Сд��object�����࣬����ʵ��������ķ�����
{
	isA : function(aType) //һ���ж�������֮���Լ���������֮���ϵ�Ļ�������
	{
		var self = this;
		while (self) {
			if (self == aType)
				return true;
			// Type���ԣ�
			self = self.Type;
		}
		;
		return false;
	}
};

function Class(aBaseClass, aClassDefine) //������ĺ��������������༰�̳й�ϵ
{
	function class_() //���������ʱ������
	{
		this.Type = aBaseClass; //���Ǹ�ÿһ����Լ��һ��Type���ԣ�������̳е���
		for ( var member in aClassDefine)
			this[member] = aClassDefine[member]; //�������ȫ�����嵽��ǰ��������
	}
	;
	class_.prototype = aBaseClass;
	return new class_();
};

function New(aClass, aParams) //��������ĺ���������������Ķ��󴴽�
{
	function new_() //�����������ʱ������
	{
		this.Type = aClass; //����Ҳ��ÿһ������Լ��һ��Type���ԣ��ݴ˿��Է��ʵ�������������
		if (aClass.Create)
			aClass.Create.apply(this, aParams); //����Լ��������Ĺ��캯������Create�����DELPHI�Ƚ�����
	}
	;
	new_.prototype = aClass;
	return new new_();
};

//�﷨��¶��Ӧ��Ч����    
var Person = Class(object, //������object������
{
	Create : function(name, age) {
		this.name = name;
		this.age = age;
	},
	SayHello : function() {
		alert("Hello, I'm " + this.name + ", " + this.age + " years old.");
	}
});

var Employee = Class(Person, //������Person�࣬�ǲ��Ǻ�һ��������Ժ����ƣ�
{
	Create : function(name, age, salary) {
		Person.Create.call(this, name, age); //���û���Ĺ��캯��
		this.salary = salary;
	},
	ShowMeTheMoney : function() {
		alert(this.name + " $" + this.salary);
	}
});

var BillGates = New(Person, [ "Bill Gates", 53 ]);
var SteveJobs = New(Employee, [ "Steve Jobs", 53, 1234 ]);
BillGates.SayHello();
SteveJobs.SayHello();
SteveJobs.ShowMeTheMoney();

var LittleBill = New(BillGates.Type, [ "Little Bill", 6 ]); //����BillGate�����ʹ���LittleBill
LittleBill.SayHello();

alert(BillGates.isA(Person)); //true
alert(BillGates.isA(Employee)); //false
alert(SteveJobs.isA(Person)); //true
alert(Person.isA(Employee)); //false
alert(Employee.isA(Person)); //true

