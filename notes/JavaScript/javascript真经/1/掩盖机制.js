//�Ⱦ�̬�������Ը�������ǣ����ǿ�����ʱ��ԭ�Ͷ���̬����µ����Ժͷ�����
//�Ӷ���̬����չ����Ĺ������ԡ����ھ�̬�����������Ǻ��������

function Person(name) {
	this.name = name;
};

Person.prototype.SayHello = function() //��������ǰ����ķ���
{
	alert("Hello, I'm " + this.name);
};

var BillGates = new Person("Bill Gates"); //��������

BillGates.SayHello();

//����������ٶ�̬��չԭ�͵ķ�����ע�����ڶ����������޸���
Person.prototype.Retire = function() 
{
	alert("Poor " + this.name + ", bye bye!");
};

BillGates.Retire(); //��̬��չ�ķ������ɱ���ǰ�����Ķ�����������
