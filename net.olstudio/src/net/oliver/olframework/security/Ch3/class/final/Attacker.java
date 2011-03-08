class Attacker {
     MyDate2 d=new MyDate2();
     public MyDate getMyDate(){
            MyDate t=d;
            return(t);
      }
}


class MyDate2 extends MyDate{
   public int getYear(){
         return(3000);
    }
}
