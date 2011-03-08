
//犹他州报税具体实现
public class UltahTax extends BasicTax
{
 // 实现抽象方法
 protected override float calculateStateTax()
 {
  return income*0.24F;
 }

 protected override float calculateFedTax()
 {
  return income*0.23F;
 }
}