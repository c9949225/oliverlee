
/*JSON����ʽ�����ô�����{}���Ű�����������Ŀ�б�ÿһ����Ŀ�䲢�ö��š�,���ָ���
 * ����Ŀ������ð�š�:���ָ���������������ֵ�����ǵ��͵��ֵ��ʾ��ʽ��
 * Ҳ�ٴα����� JavaScript��Ķ�������ֵ�ṹ��*/


/*����Ҫ�����JSON�ַ������һ��JavaScript����ʱ��ֻ��Ҫʹ��eval�������ǿ�������ת�����棬
 * �������ܵõ�һ�� JavaScript�ڴ����*/



//  ����һ��û���κ����ԵĶ���
var o = {};

//����һ�������������Լ���ʼֵ��
var person = {name: "Angel", age: 18, married: false};

//����һ�������������Ժͷ�����
var speaker = {text: "Hello World", say: function(){alert(this.text)}};

//����һ�������ӵĶ���Ƕ����������Ͷ�������ȣ�
var company =
{
    name: "Microsoft",
    product: "softwares",
    chairman: {name: "Bill Gates", age: 53, Married: true},
    employees: [{name: "Angel", age: 26, Married: false}, {name: "Hanson", age: 32, Marred: true}],
    readme: function() {document.write(this.name + " product " + this.product);}
};






