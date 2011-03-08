import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

public class CheckSign{

 
   // ��ǩ�����Ƕ��õ�msg.dat��sign.dat�͹�ԿSkey_RSA_pub.dat
    // �޸�msg.dat�������޷�ͨ����֤����˲������紫�ݹ����б��޸ģ���signer�����ϣ���Ϊֻ��signer�������msg.dat�õ�sign.dat��
     //    ���checker�����ݸ�Ϊ$300����signer��Ҫ������ǩ����֤��

// 1.��֤������  checker�����Ƿ����紫��ʱ�������޸Ĺ��ˣ������Ƿ�ȷʵ��signer�����ģ����Ǳ����Ҵ��ģ������ǩ������ȷ�����Ƿ��ģ�

//2. ���ɷ����ԡ� signer����������ļ����Լ����ģ���˵checker�Ĺ������ˣ�checkerչʾ��ǩ��sign.dat��ֻ��signer��˽Կ���ܸ���msg.dat�������������ݣ����˶����ܡ�   
   public static void main(String args[ ]) throws Exception{
          //��ȡ���ݣ�����data����
        FileInputStream f=new FileInputStream("msg.dat");
        int num=f.available();
        byte[ ] data=new byte[num];
        f.read(data);
       //��ǩ��
        FileInputStream f2=new FileInputStream("Sign.dat");
        int num2=f2.available();
        byte[ ] signeddata=new byte[num2];
        f2.read(signeddata);
    //����Կ
        FileInputStream f3=new FileInputStream("Skey_RSA_pub.dat");
        ObjectInputStream b=new ObjectInputStream(f3);
        RSAPublicKey  pbk=(RSAPublicKey)b.readObject( );
        //��ȡ����
        Signature s=Signature.getInstance("MD5WithRSA");
       //��ʼ��
        s.initVerify(pbk);
        //����ԭʼ����
        s.update(data);
        boolean ok=false;
        try{
           //��ǩ����֤ԭʼ����
           ok=  s.verify(signeddata);
           System.out.println(ok);
        }
        catch(SignatureException e){ System.out.println(e);}

        System.out.println("Check Over");
  }

}  