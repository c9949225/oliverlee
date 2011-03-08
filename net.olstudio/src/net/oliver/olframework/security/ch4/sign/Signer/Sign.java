import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;


//msg.dat��Ų����޸ĵ���Ϣ
//Skey_RSA_priv.dat Ϊ��ǰ��˽Կ���Լ����ܱ��棬���Լ���˽Կǩ��
// java sign�� ������ǩ���ļ�sign.dat
// ��msg.dat��sign.datһ�����һ���ˣ�ͬʱ����Կ����

//���н����

public class Sign{

 
    
   public static void main(String args[ ]) throws Exception{
          //��ȡҪǩ�������ݣ�����data����
        FileInputStream f=new FileInputStream("msg.dat");
        int num=f.available();
        byte[ ] data=new byte[num];
        f.read(data);

        //��ȡ˽Կ

        FileInputStream f2=new FileInputStream("Skey_RSA_priv.dat");
        ObjectInputStream b=new ObjectInputStream(f2);
        RSAPrivateKey prk=(RSAPrivateKey)b.readObject( );

        //��ȡSignature����
        Signature s=Signature.getInstance("MD5WithRSA");
         //��ʼ��
        s.initSign(prk);
         //����Ҫǩ��������
        s.update(data);
        System.out.println("");
         //ǩ��
        byte[ ] signeddata=s.sign( );
        for(int i=0;i<data.length;i++){
               System.out.print(signeddata[i]+",");
        }
        //����ǩ��
        FileOutputStream  f3=new FileOutputStream("Sign.dat");
        f3.write(signeddata);

       
  }

}  