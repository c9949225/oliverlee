public class TestVeri{
    public static void main(String args[] ){
        MyDate4 d=new MyDate4( );
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

class MyDate4{
    public  int year, month, day;
    public void setDay(int d){
        //���ֺϷ��Լ��
        //......
        day=d;
    }
    public int getDay( ){
        return(day);
    }
}

