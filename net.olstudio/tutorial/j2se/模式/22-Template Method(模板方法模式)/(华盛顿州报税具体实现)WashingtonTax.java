//��ʢ���ݱ�˰����ʵ��
public calss WashingtonTax extends BasicTax
{
 //ʵ�ֳ��󷽷�
 protected override float calculateStateTax()
 {
  return income*0.12F;
 }

 protected override float calculateFedTax()
 {
  return income*0.23F;
 }
 
}
