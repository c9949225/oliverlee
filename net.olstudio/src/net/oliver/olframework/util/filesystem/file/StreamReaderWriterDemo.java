package net.oliver.olframework.util.filesystem.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * �����InputStream��OutputStream�����ַ�����
 * ����ʹ��java.io.InputStreamReader��java.io.OutputStreamWriter(���Ƿֱ�ΪReader��Writer������)Ϊ������ַ�����Ĺ���
 * 
 * �ٸ�������˵������Ҫ��ʾ���ı��ļ������ݣ� ���÷��ĵ������ж��ַ�����(���緶��14.15��Ҫ���ĵ������ж���ASCIIӢ����ĸ��BIG5������)��
 * ֻҪ��InputStream�� OutputStream��ʵ����Ϊ����InputStreamReader��OutputStreamWriterʱ�ı�����
 * �Ϳ��Բ��� InputStreamReader��OutputStreamWriter�������ı��ļ��Ķ�ȡ��������Ϊ�����ַ��ж���ת���Ķ�����
 * 
 */
public class StreamReaderWriterDemo {

	public static void main(String[] args) {

		try {

			FileInputStream fileInputStream = new FileInputStream(args[0]);
			// ΪFileInputStream�����ַ�������
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

			FileOutputStream fileOutputStream = new FileOutputStream("backup_" + args[0]);
			
			// ΪFileOutputStream�����ַ�������
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

			int ch = 0;
			// ���ַ���ʽ��ʾ�ļ�����
			while ((ch = inputStreamReader.read()) != -1) {
				System.out.print((char) ch);
				outputStreamWriter.write(ch);
			}
			System.out.println();
			inputStreamReader.close();
			outputStreamWriter.close();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("û��ָ���ļ�");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
