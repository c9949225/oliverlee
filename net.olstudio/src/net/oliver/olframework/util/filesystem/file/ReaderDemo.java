package net.oliver.olframework.util.filesystem.file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;

/**
 * java.io.Reader、java.io.Writer与其子类等是处理字符流(Character Stream)的相关类。
 * 简单地说，就是对流数据以一个字符(两个字节)的长度为单位来处理(0~65 535、0x0000～0xffff)，并进行适当的字符编码转换处理，
 * 即Reader、Writer与其子类可以用于进行所谓纯文本文件的字符读/写
 * 
 * java.io.Reader和java.io.Writer支持Unicode标准字符集(Character Set)在处理流数据时，会根据系统默认的字符编码来进行字符转换，
 * 
 * (字节流则只支持ISO-Latin-1 8-bit)。
 * 
 * Reader和Writer是抽象类，
 * 
 * 在进行文本文件的字符读写时真正会使用其子类，子类通常会重新定义相关的方法。
 * 
 * 
 * 上一个例子是读入一个含BIG5中文字及 ASCII字符的文本文件，当时对BIG5中文字的处理是新建一个String实例。
 * 
 * 这里改写一下该范例，使用Reader的子类java.io.InputStreamReader来转换读入的两个字节为中文字符，并显示在屏幕上。
 *
 */
public class ReaderDemo {

	public static void main(String[] args) {

		try {

			PushbackInputStream pushbackInputStream = new PushbackInputStream(new FileInputStream(args[0]));

			byte[] array = new byte[2];// 2个字节数组

			ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(array);

			// reader会从已读的位数组中取出数据

			InputStreamReader reader = new InputStreamReader(byteArrayStream);

			int tmp = 0;

			int count = 0;

			while ((count = pushbackInputStream.read(array))!= -1) {

				// 两个字节转换为整数
				tmp = (short) ((array[0] << 8) | (array[1] & 0xff));
				tmp = tmp & 0xFFFF;

				// 判断是否为BIG5，如果是则显示BIG5中文字

				if (tmp >= 0xA440 && tmp < 0xFFFF) {

					System.out.println("BIG5: " +  (char) reader.read());

					// 重置ArrayInputStream的读取光标
					// 下次reader才会再重头读取数据
					byteArrayStream.reset();
				}
				else {

					// 将第二个字节推回流

					pushbackInputStream.unread(array, 1, 1);

					// 显示ASCII范围的字符

					System.out.println("ASCII: " +

					(char) array[0]);

				}

			}

			pushbackInputStream.close();

		}

		catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("请指定文件名称");

		}

		catch (IOException e) {

			e.printStackTrace();

		}

	}

}
