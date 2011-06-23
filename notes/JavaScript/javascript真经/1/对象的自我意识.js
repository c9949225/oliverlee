

/*  JavaScript��thisȴ����һ����this�������ң�Ҳ�������㣬�����������������������㣬�������ң�
 *  ��Ͳ�����ԭ�����Ǹ������ҡ������ JavaScript���this�ĺ�����
 *
 *  ��JavaScript�����У���ֻ�ܰ�this���ɵ�ǰҪ����ġ����������
 *  this��һ����������ò���������this�����������Է��ʵ�������� ��������Ժͷ�����
 *  ��ȴ���ܸ�this������ֵ��
 *  
 *   JavaScript�ṩ�˴���this�����Ķ�����ʽ���ֶΣ����У�
 *   ��BillGates.WhoAmI()��SteveJobs.WhoAmI()������ʽ���Ǵ���this�������������ʽ��
 *   ��ʱ��this���Ǻ��������Ķ�����
 *   
 *   
 *
 **/

function WhoAmI() //����һ������WhoAmI
{
	alert("I'm " + this.name + " of " + typeof (this));
};

//��ʱ��this��ǰ��δ����ȫ�ֶ���
//��������о���window������name����Ϊ���ַ�����
//�����I'm of object
WhoAmI();  

var BillGates = {name: "Bill Gates"};
BillGates.WhoAmI = WhoAmI;  //������WhoAmI��ΪBillGates�ķ�����
BillGates.WhoAmI();         //��ʱ��this��BillGates�������I'm Bill Gates of object

var SteveJobs = {name: "Steve Jobs"};
SteveJobs.WhoAmI = WhoAmI;  //������WhoAmI��ΪSteveJobs�ķ�����
SteveJobs.WhoAmI();         //��ʱ��this��SteveJobs�������I'm Steve Jobs of object

WhoAmI.call(BillGates); //ֱ�ӽ�BillGates��Ϊthis������WhoAmI�������I'm Bill Gates of object
WhoAmI.call(SteveJobs); //ֱ�ӽ�SteveJobs��Ϊthis������WhoAmI�������I'm Steve Jobs of object

//thisֻ������������functionԪ�ؽ��ʱ��һ��������ֽ�ϱ���һ��������Ե�Ĭ�Ͻ�ϸ������`���Եø��ӳ�Ȼ�����ѡ�
//��SteveJobs��Ϊthis��ȴ����BillGates��WhoAmI�����������I'm Steve Jobs of object
BillGates.WhoAmI.call(SteveJobs);
//��BillGates��Ϊthis��ȴ����SteveJobs��WhoAmI�����������I'm Bill Gates of object
SteveJobs.WhoAmI.call(BillGates);

WhoAmI.WhoAmI = WhoAmI;     //��WhoAmI��������Ϊ����ķ�����
WhoAmI.name = "WhoAmI";
WhoAmI.WhoAmI();            //��ʱ��this��WhoAmI�����Լ��������I'm WhoAmI of function

//��ʱ����һ�����������������Ժ����WhoAmI�����������I'm nobody of object
({name: "nobody", WhoAmI: WhoAmI}).WhoAmI();   



