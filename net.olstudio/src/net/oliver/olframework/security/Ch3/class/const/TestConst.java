public class TestConst{
    //������Ķ���
    final  int x[ ]={1,2,3};
    //������Ķ���
    final NewConst y=new NewConst();
    public static void main(String args[] ){
        TestConst t=new TestConst( );
        //ִ��go( )������ͼ�޸ĳ���x��y�е�����
        t.go( );
        //�鿴����x������
        int[ ] x1=t.x;
        System.out.println("The code with problem");
        for(int i=0;i<x1.length;i++){
            System.out.println(x1[i]);
        }
       //�鿴����y������
        NewConst x2=t.y;
        System.out.println("The code without problem");
        for(int i=0;i<x2.getConst().length;i++){
            System.out.println(x2.getConst()[i]);
        }


    }
    void go(){
        //��ͼ�޸ĳ���x��y�е�����
        x[0]=20;
        y.getConst()[0]=20;
    }
}


class NewConst{
    private final  int x[ ]={1,2,3};
    public final int[ ] getConst( ){
        int [] s=(int []) x.clone();
        return(s);
    }
}
