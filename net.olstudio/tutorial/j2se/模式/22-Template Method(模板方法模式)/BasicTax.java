public abstract class BasicTax
{
 protected float income;//�������

 public void ReportTax()
 {
  //����һ����ø�������������˰
  float sTax = calculateStateTax();
  //����һ����ø���������������˰
  float fTax = calcualteFedTax();

  //�������Ʋ�û
  bool ok = CheckBankBalance(sTax + fTax);

  if(!ok)
  {
   //�����Ʋ�
   FileBankruptcy();
  }
  else
  {
   //���û�Ʋ��Ͱ�˰���ϰ�
   SendMoneyToGov(sTax + fTax);
  }
 }

 // ���ݲ�ͬ��������ʵ����������������㷽��
 protected abstract float calculateStateTax();
 protected abstract float calculateFedTax();
 
}