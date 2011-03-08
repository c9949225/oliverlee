//华盛顿州报税具体实现
public calss WashingtonTax extends BasicTax
{
 //实现抽象方法
 protected override float calculateStateTax()
 {
  return income*0.12F;
 }

 protected override float calculateFedTax()
 {
  return income*0.23F;
 }
 
}
