function SayHello() //�ȶ���һ��SayHello��������
{
	alert("Hello, I'm " + this.name);
};

function Person(name) //�������Ĺ��캯��
{
	this.name = name; //������ֵ������this���������
	this.SayHello = SayHello; //��this����SayHello������ֵΪǰ���Ƿ�SayHello���롣
};

var BillGates = new Person("Bill Gates"); //����BillGates����
var SteveJobs = new Person("Steve Jobs"); //����SteveJobs����

alert(BillGates.SayHello == SteveJobs.SayHello); //��ʾ��true
