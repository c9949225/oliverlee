public class TestVar{
    public static void main(String args[] ){
        MyDate3 d=new MyDate3( );
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
