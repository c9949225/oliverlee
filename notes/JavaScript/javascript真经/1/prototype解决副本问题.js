

function Person(name) {
	this.name = name; //���ö������ԣ�ÿ���������һ����������
};

Person.prototype.SayHello = function() //��Person������prototype���SayHello������
{
	alert("Hello, I'm " + this.name);
}

var BillGates = new Person("Bill Gates"); //����BillGates����
var SteveJobs = new Person("Steve Jobs"); //����SteveJobs����

BillGates.SayHello(); //ͨ��BillGates����ֱ�ӵ��õ�SayHello����
SteveJobs.SayHello(); //ͨ��SteveJobs����ֱ�ӵ��õ�SayHello����

//��Ϊ���������ǹ���prototype��SayHello��������ʾ��true
alert(BillGates.SayHello == SteveJobs.SayHello);