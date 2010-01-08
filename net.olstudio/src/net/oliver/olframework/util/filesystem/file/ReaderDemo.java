package net.oliver.olframework.util.filesystem.file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;

/**
 * java.io.Reader��java.io.Writer����������Ǵ����ַ���(Character Stream)������ࡣ
 * �򵥵�˵�����Ƕ���������һ���ַ�(�����ֽ�)�ĳ���Ϊ��λ������(0~65 535��0x0000��0xffff)���������ʵ����ַ�����ת������
 * ��Reader��Writer��������������ڽ�����ν���ı��ļ����ַ���/д
 * 
 * java.io.Reader��java.io.Writer֧��Unicode��׼�ַ���(Character Set)�ڴ���������ʱ�������ϵͳĬ�ϵ��ַ������������ַ�ת����
 * 
 * (�ֽ�����ֻ֧��ISO-Latin-1 8-bit)��
 * 
 * Reader��Writer�ǳ����࣬
 * 
 * �ڽ����ı��ļ����ַ���дʱ������ʹ�������࣬����ͨ�������¶�����صķ�����
 * 
 * 
 * ��һ�������Ƕ���һ����BIG5�����ּ� ASCII�ַ����ı��ļ�����ʱ��BIG5�����ֵĴ������½�һ��Stringʵ����
 * 
 * �����дһ�¸÷�����ʹ��Reader������java.io.InputStreamReader��ת������������ֽ�Ϊ�����ַ�������ʾ����Ļ�ϡ�
 *
 */
public class ReaderDemo {

	public static void main(String[] args) {

		try {

			PushbackInputStream pushbackInputStream = new PushbackInputStream(new FileInputStream(args[0]));

			byte[] array = new byte[2];// 2���ֽ�����

			ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(array);

			// reader����Ѷ���λ������ȡ������

			InputStreamReader reader = new InputStreamReader(byteArrayStream);

			int tmp = 0;

			int count = 0;

			while ((count = pushbackInputStream.read(array))!= -1) {

				// �����ֽ�ת��Ϊ����
				tmp = (short) ((array[0] << 8) | (array[1] & 0xff));
				tmp = tmp & 0xFFFF;

				// �ж��Ƿ�ΪBIG5�����������ʾBIG5������

				if (tmp >= 0xA440 && tmp < 0xFFFF) {

					System.out.println("BIG5: " +  (char) reader.read());

					// ����ArrayInputStream�Ķ�ȡ���
					// �´�reader�Ż�����ͷ��ȡ����
					byteArrayStream.reset();
				}
				else {

					// ���ڶ����ֽ��ƻ���

					pushbackInputStream.unread(array, 1, 1);

					// ��ʾASCII��Χ���ַ�

					System.out.println("ASCII: " +

					(char) array[0]);

				}

			}

			pushbackInputStream.close();

		}

		catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("��ָ���ļ�����");

		}

		catch (IOException e) {

			e.printStackTrace();

		}

	}

}
