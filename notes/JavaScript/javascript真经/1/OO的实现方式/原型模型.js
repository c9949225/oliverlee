//ԭ��ģ����Ҫһ�����캯�����������ĳ�Ա��������ȴ�����ڸù��캯����ԭ���ϡ�

//ԭ����ģ����Ȼ����ģ��������˽�б���������ҲҪ���������������࣬�Եò���ô�����š���
//�����������ķ����ǹ���ģ��������������������⣬�����������ڡ��հ���ģ�͡�
//����ν����ʧ���еá��

// ��ԭ��ģ���У�Ϊ��ʵ����̳У��������Ƚ����๹�캯����prototype����Ϊһ������Ķ���ʵ����
// ��������������ʵ����Ŀ�ľ���Ϊ�˹���ԭ���������𵽹����ϲ�ԭ�ͷ������á�
 
//���幹�캯��
function Person(name) {
	this.name = name; //�ڹ��캯���ж����Ա
};

//�������嵽���캯����prototype��
Person.prototype.SayHello = function() {
	alert("Hello, I'm " + this.name);
};

//���๹�캯��
function Employee(name, salary) {
	Person.call(this, name); //�����ϲ㹹�캯��
	this.salary = salary; //��չ�ĳ�Ա
};

//���๹�캯��������Ҫ���ϲ㹹�캯��������prototype����ʵ�ּ̳еĸ���
Employee.prototype = new Person();

//ֻ��Ҫ��prototype�ķ������˶���ĳ�Աû���κ����壡

//���෽��Ҳ���嵽���캯��֮��
Employee.prototype.ShowMeTheMoney = function() {
	alert(this.name + " $" + this.salary);
};

var BillGates = new Person("Bill Gates");
BillGates.SayHello();

var SteveJobs = new Employee("Steve Jobs", 1234);
SteveJobs.SayHello();
SteveJobs.ShowMeTheMoney();
