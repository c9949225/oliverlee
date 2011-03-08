public class TestRef{
    public static void main(String args[] ){
        MyDate5 d=new MyDate5( );
        d.setDay(10);
        int day=d.getDay();
        System.out.println(day);
        //跳过getDay( )方法直接修改私有成员变量
        int x[ ]=d.getDate( );
               x[2]=100;
        day=d.getDay();
        System.out.println(day);
    }
}
