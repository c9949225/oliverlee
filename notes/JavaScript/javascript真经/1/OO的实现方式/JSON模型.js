var Person = //����һ����������Ϊԭ����
{
	Create : function(name, age) //��������캯��
	{
		this.name = name;
		this.age = age;
	},
	SayHello : function() //���巽��
	{
		alert("Hello, I'm " + this.name);
	},
	HowOld : function() //���巽��
	{
		alert(this.name + " is " + this.age + " years old.");
	}
};


//���������ĳ����ʽ���������󣬲�����������õ�ԭ������Ϊ����������ࡱ����
//�����൱�ڴ�������Ķ�������
//���ź����ǣ����Ǽ������ܷ��ʵ��������õ�ԭ�����ԣ�������Щ��������Է��ʵ�����
//������ԭ�ͣ����������Ļ���ֻ���޶����û�����ʹ���������������Ҳ���������С�

//��ô�����ǿɲ�����ͨ��һ��������������ý�飬
//���øú��������prototype��������ת���ԭ�ͣ�����new���������ݸ��½��Ķ����أ�

function anyfunc(){};           //����һ����������
anyfunc.prototype = Person;     //��ԭ�Ͷ���ŵ���תվprototype
var BillGates = new anyfunc();  //�½����������ԭ�ͽ�������������ԭ�Ͷ���

//���������anyfunc����ֻ��һ�����ǣ���ʹ�ù��������֮�����ͳ��˶���Ķ����ˣ�
//�������ֱ��ʹ�ù��캯������������Ҳûɶ��ͬ���е㲻ˬ��

//������ǽ���Щ����д��һ��ͨ�ú��������Ǹ���������Ҳ�ͳ��˺����ڵĺ�����
//����ڲ��������Ϳ�������㺯���˳���������Զ�������

//ͨ�ô�������
function New(aClass, aParams) 
{
	function new_() //������ʱ����ת������
	{
		//����ԭ���ж���ĵĹ��캯������ת�����߼����������
		aClass.Create.apply(this, aParams); 
	}
	;
	new_.prototype = aClass; //׼����תԭ�Ͷ���
	return new new_(); //���ؽ������ս����Ķ���
	// new_�����������New�����˳���������Զ�����
};

var Person = //�������
{
	// ���캯��
	Create : function(name, age) {
		this.name = name;
		this.age = age;
	},
	SayHello : function() {
		alert("Hello, I'm " + this.name);
	},
	HowOld : function() {
		alert(this.name + " is " + this.age + " years old.");
	}
};

//����ͨ�ú����������󣬲���������ʽ���ݹ������
var BillGates = New(Person, [ "Bill Gates", 53 ]);
BillGates.SayHello();
BillGates.HowOld();

alert(BillGates.constructor == Object); //�����true

//�����ͨ�ú���New()����һ�����﷨��¶��������﷨��¶������ת��ԭ�Ͷ��󣬻���ת�˹��캯���߼������������
