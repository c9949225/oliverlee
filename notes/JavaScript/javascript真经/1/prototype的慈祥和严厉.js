//�κ�һ��������Ȼ�����������أ���ȴ���ܶ�ԭ�Ͷ�����еĲƲ���
//��Ϊ�ǿ��ܻ�Ӱ�쵽�������ӵ����档

function Person(name) {
	this.name = name;
};

Person.prototype.company = "Microsoft"; //ԭ�͵�����

Person.prototype.SayHello = function() //ԭ�͵ķ���
{
	alert("Hello, I'm " + this.name + " of " + this.company);
};

//���ڼ̳���ԭ�͵Ķ��������ؾ������Hello, I'm Bill Gates
var BillGates = new Person("Bill Gates");
BillGates.SayHello(); 


var SteveJobs = new Person("Steve Jobs");
//�����Լ���company���ԣ��ڸ���ԭ�͵�company����
SteveJobs.company = "Apple"; 
//ʵ�����Լ���SayHello�������ڸ���ԭ�͵�SayHello����
SteveJobs.SayHello = function()
{
	alert("Hi, " + this.name + " like " + this.company + ", ha ha ha ");
};

SteveJobs.SayHello(); //�����Լ����ǵ����Ժͷ����������Hi, Steve Jobs like Apple, ha ha ha 
BillGates.SayHello(); //SteveJobs�ĸ���û��Ӱ��ԭ�Ͷ���BillGates���ǰ����������
