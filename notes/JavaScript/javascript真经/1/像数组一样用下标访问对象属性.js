/*
 * ����Ӧ�������������ݽṹ���������ݽṹһ����һ���Ĺ��ɣ��ʺϽ���ͳһ���������������ȣ�
 * �е��񲨡�
 * ����������ɢ���ݽṹ���ʺ�������ɢ�ĺ͸��Ի��Ķ������е������ӡ�
 * ��ˣ�����Ҳ���������ʣ�JavaScript��Ķ��󵽵��ǲ��������ӣ�
 * ������ڶ��������ۣ���ô��һ���ǣ����������ԣ�
 * */

var anObject = {};  // �½�һ������

anObject.aProperty = "Property of object";  // �����һ������
anObject.aMethod = function(){alert("Method of object")}; // �����һ������
    
// ��Ҫ�����棺
alert(anObject["aProperty"]);   // ���Խ�������������������Ϊ�±�����������
anObject["aMethod"]();          // ���Խ����������Է�������Ϊ�±������÷���

for( var s in anObject)           // ����������������Ժͷ������е���������
        alert(s + " is a " + typeof(anObject[s]));

//    ͬ������function���͵Ķ���Ҳ��һ����

var aFunction = function() {};  // һ��������Ҳ��һ������

aFunction.aProperty = "Property of function";  // ������һ������
aFunction.aMethod = function(){alert("Method of function")}; // ������һ������
    
// ��Ҫ�����棺
alert(aFunction["aProperty"]);   // ���Խ���������������������Ϊ�±�����������
aFunction["aMethod"]();          // ���Խ������������Է�������Ϊ�±������÷���
    
for( var s in aFunction)           // �����������������Ժͷ������е���������
        alert(s + " is a " + typeof(aFunction[s]));
