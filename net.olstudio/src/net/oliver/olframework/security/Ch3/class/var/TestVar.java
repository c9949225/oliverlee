public class TestVar{
    public static void main(String args[] ){
        MyDate3 d=new MyDate3( );
        d.setDay(10);
        d.day=100;
        // ִ�и��ֲ���
        //......
        int day=d.getDay();
        //ʹ��day����������
        //...
        System.out.println(day);
    }
}
