
function MyFunc() {}; //����һ���պ���
var anObj = new MyFunc();  //ʹ��new������������MyFun�������ʹ�����һ������
 
// ��ʵ�����԰�����Ĵ����д�����ֵȼ���ʽ��
 
 
 function MyFunc(){};
 var anObj = {};     //����һ������
 MyFunc.call(anObj); //��anObj������Ϊthisָ�����MyFunc����

