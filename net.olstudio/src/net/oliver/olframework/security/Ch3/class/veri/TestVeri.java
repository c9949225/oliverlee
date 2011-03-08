public class TestVeri{
    public static void main(String args[] ){
        MyDate4 d=new MyDate4( );
        d.setDay(10);
        d.day=100;
        // 执行各种操作
        //......
        int day=d.getDay();
        //使用day做各种事情
        //...
        System.out.println(day);
    }
}

class MyDate4{
    public  int year, month, day;
    public void setDay(int d){
        //各种合法性检测
        //......
        day=d;
    }
    public int getDay( ){
        return(day);
    }
}

