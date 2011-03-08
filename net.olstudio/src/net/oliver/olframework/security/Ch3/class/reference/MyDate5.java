class MyDate5{
    private int [ ] date=new int[3];    // year, month, day;
    public void setDay(int d){
        //各种合法性检测
        //......
        date[2]=d;
    }
    public int getDay( ){
        return(date[2]);
    }
    public int[ ] getDate(){
               return(date);
      }
}
