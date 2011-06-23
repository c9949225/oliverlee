
function Person(name) //�������Ĺ��캯��
{
	this.name = name; //������ֵ������this���������
	this.SayHello = function() { //��this������һ��SayHello������
		alert("Hello, I'm " + this.name);
	}; 
};

function Employee(name, salary) //�ӹ��캯��
{
	Person.call(this, name); //��this���������캯��
	
	this.salary = salary; //����һ��this��salary����
	
	this.ShowMeTheMoney = function() {//���ShowMeTheMoney������
		alert(this.name + " $" + this.salary);
	}; 
};

var BillGates = new Person("Bill Gates"); //��Person���캯������BillGates����
var SteveJobs = new Employee("Steve Jobs", 1234); //��Empolyee���캯������SteveJobs����

BillGates.SayHello(); //��ʾ��I'm Bill Gates
SteveJobs.SayHello(); //��ʾ��I'm Steve Jobs
SteveJobs.ShowMeTheMoney(); //��ʾ��Steve Jobs $1234

alert(BillGates.constructor == Person); //��ʾ��true
alert(SteveJobs.constructor == Employee); //��ʾ��true


/*�ù��캯������this���󴴽�������ÿһ�����󣬲������и��Եĳ�Ա���ݣ����һ����и��Եķ������ݡ�
���仰˵�������Ĵ�����(���ֺ����߼�������)��ÿһ�������ж�����һ��������
����ÿһ�����븱�����߼�����ͬ�ģ���������ȷʵ�Ǹ��Ա�����һ�ݴ����塣*/

// ======================================================================
alert(BillGates.SayHello == SteveJobs.SayHello); //��ʾ��false
//======================================================================