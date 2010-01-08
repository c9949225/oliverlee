package net.oliver.olframework.util.filesystem.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ��Linux�ı�ת��ΪDos��ʽ
 * \r 13
 * \n 10
 * int���ͺ�char������ͨ�õĶ����Ա�ʾһ���ַ����ֽڱ�ʾ
 */
/*if(flag){// �������ش����嵥
 int in = 0;
 while((in = is.read()) != -1) {
 if(in == 10)// \n
 {
 sos.write(13);// \r
 sos.write(10);// \n
 }else{
 sos.write(in);
 }
 sos.flush();
 }
 }else{

 byte[] bs = new byte[10240];
 int byteRead = 0;
 while ((byteRead = is.read(bs)) > 0)
 {
 sos.write(bs, 0, byteRead);
 sos.flush();
 }
 }*/
public class UnixToDos {

	public static void main(String[] args) {
		try {
			FileReader fileReader = new FileReader("D:\\1234.txt");
			FileWriter fileWriter = new FileWriter("D:\\new.txt");
			int in = 0;
			char[] wlnChar = { '\r', '\n' };
			while ((in = fileReader.read()) != -1) {
				if (in == '\n') {
					// д��"\r\n"
					fileWriter.write(wlnChar);
				}
				else
					fileWriter.write(in);
			}
			fileReader.close();
			fileWriter.close();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("��ָ���ļ�");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
