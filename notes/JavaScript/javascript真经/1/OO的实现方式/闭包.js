/* ��ν�ġ��հ����������ڹ��캯�����ڶ�������ĺ�����ΪĿ�����ķ���������
 ���������ķ�������������������㺯�����е���ʱ������
 ��ʹ��ֻҪĿ���������������ʼ���ܱ����䷽����
 ���ܼ�ӱ���ԭ���캯���嵱ʱ�õ�����ʱ����ֵ��
 �����ʼ�Ĺ��캯�������Ѿ���������ʱ����������Ҳ����ʧ�ˣ�
 ����Ŀ�����ķ�����ȴʼ�������õ��ñ�����ֵ�����Ҹ�ֵֻ��ͨ���ַ��������ʡ�*/
 
/*��ÿһ����������һ�ݷ�����һ�ֺܴ���˷ѡ����У����հ������ּ�ӱ��ֱ���ֵ�Ļ��ƣ�
�������JavaSript�������������������⡣�ر�����������临�ӵ�ѭ������ʱ��
�������յ��ж��߼��ǳ����ӡ��޶���ż��IE��������ڰ汾ȷʵ����JavaSript��������
������ڴ�й©���⡣�ټ��ϡ��հ���ģ�������ܲ��Է���ı��ֲ��ѣ�
΢�����շ����ˡ��հ���ģ�ͣ������á�ԭ�͡�ģ�͡�����ν���еñ���ʧ���*/

function Person(firstName, lastName, age) {
	//˽�б�����
	var _firstName = firstName;
	var _lastName = lastName;

	//��������:
	this.age = age;

	//������
	this.getName = function() {
		// �������˽�б���
		return (firstName + " " + lastName);
	};
	
	this.SayHello = function() {
		alert("Hello, I'm " + firstName + " " + lastName);
	};
};

var BillGates = new Person("Bill", "Gates", 53);
var SteveJobs = new Person("Steve", "Jobs", 53);

BillGates.SayHello();
SteveJobs.SayHello();
alert(BillGates.getName() + " " + BillGates.age);
//�ر��ǡ��հ������ƿ���ģ���˽�г�Ա�ı������ƣ����÷ǳ�Ư����
alert(BillGates.firstName); //���ﲻ�ܷ��ʵ�˽�б���


