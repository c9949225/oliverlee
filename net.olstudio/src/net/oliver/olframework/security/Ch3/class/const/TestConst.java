public class TestConst{
    //有问题的定义
    final  int x[ ]={1,2,3};
    //无问题的定义
    final NewConst y=new NewConst();
    public static void main(String args[] ){
        TestConst t=new TestConst( );
        //执行go( )方法试图修改常量x和y中的内容
        t.go( );
        //查看常量x的内容
        int[ ] x1=t.x;
        System.out.println("The code with problem");
        for(int i=0;i<x1.length;i++){
            System.out.println(x1[i]);
        }
       //查看常量y的内容
        NewConst x2=t.y;
        System.out.println("The code without problem");
        for(int i=0;i<x2.getConst().length;i++){
            System.out.println(x2.getConst()[i]);
        }


    }
    void go(){
        //试图修改常量x和y中的内容
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
