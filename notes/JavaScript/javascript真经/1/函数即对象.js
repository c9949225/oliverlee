/*
 * Sing������������ָ�Sing������̬��������author��poem���ԡ�
 * �����������JavaScript�������Ƕ���ı���
 * 
 * */
function Sing() {
	with(arguments.callee)
		alert(author + "��" + poem);
};

Sing.author = "���";
Sing.poem = "�����ص��£���Ӱ��������һ����ص�������ȥ����";
Sing();

Sing.author = "��ս";
Sing.poem = "�ճ������죬������ɽǰ��Ů������Թ���ѳ���ǧ��";
Sing();
