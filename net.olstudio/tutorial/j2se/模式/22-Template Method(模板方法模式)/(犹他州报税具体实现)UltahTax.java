
//�����ݱ�˰����ʵ��
public class UltahTax extends BasicTax
{
 // ʵ�ֳ��󷽷�
 protected override float calculateStateTax()
 {
  return income*0.24F;
 }

 protected override float calculateFedTax()
 {
  return income*0.23F;
 }
}