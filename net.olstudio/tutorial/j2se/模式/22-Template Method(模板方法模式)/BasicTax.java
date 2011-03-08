public abstract class BasicTax
{
 protected float income;//你的收入

 public void ReportTax()
 {
  //计算一下你该给州政府交多少税
  float sTax = calculateStateTax();
  //计算一下你该给联邦政府交多少税
  float fTax = calcualteFedTax();

  //算算你破产没
  bool ok = CheckBankBalance(sTax + fTax);

  if(!ok)
  {
   //申请破产
   FileBankruptcy();
  }
  else
  {
   //如果没破产就把税交上吧
   SendMoneyToGov(sTax + fTax);
  }
 }

 // 根据不同的政策来实现下面的这两个计算方法
 protected abstract float calculateStateTax();
 protected abstract float calculateFedTax();
 
}