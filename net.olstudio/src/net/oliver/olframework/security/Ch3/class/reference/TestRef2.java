public class TestRef2{
    public static void main(String args[] ){
        MyDate6 d=new MyDate6( );
        d.setDay(10);
        int day=d.getDay();
        System.out.println(day);
        //����getDay( )����ֱ���޸�˽�г�Ա����
        int x[ ]=d.getDate( );
               x[2]=100;
        day=d.getDay();
        System.out.println(day);
    }
}
