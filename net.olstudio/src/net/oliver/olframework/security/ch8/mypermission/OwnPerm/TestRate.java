import my.test.*;
import java.io.*;
import java.security.*;
public class TestRate{
     public static void main(String args[]){
            my.test.MyRate mr=new my.test.MyRate( );
            System.out.println(mr.getRate());
            try{
                 mr.setRate(0.6);
            }
            catch(AccessControlException e){
                 System.out.println(e);
            }
            System.out.println(mr.getRate());
     }
}
