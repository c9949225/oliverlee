package net.oliver.olframework.util.filesystem.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 若想对InputStream和OutputStream进行字符处理，
 * 可以使用java.io.InputStreamReader和java.io.OutputStreamWriter(它们分别为Reader和Writer的子类)为其加上字符处理的功能
 * 
 * 举个例子来说，若想要显示纯文本文件的内容， 不用费心地自行判断字符编码(例如范例14.15中要费心地自行判断是ASCII英文字母或BIG5中文字)，
 * 只要将InputStream、 OutputStream的实例作为构建InputStreamReader、OutputStreamWriter时的变量，
 * 就可以操作 InputStreamReader和OutputStreamWriter来进行文本文件的读取，让它们为您做字符判断与转换的动作。
 * 
 */
public class StreamReaderWriterDemo {

	public static void main(String[] args) {

		try {

			FileInputStream fileInputStream = new FileInputStream(args[0]);
			// 为FileInputStream加上字符处理功能
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

			FileOutputStream fileOutputStream = new FileOutputStream("backup_" + args[0]);
			
			// 为FileOutputStream加上字符处理功能
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

			int ch = 0;
			// 以字符方式显示文件内容
			while ((ch = inputStreamReader.read()) != -1) {
				System.out.print((char) ch);
				outputStreamWriter.write(ch);
			}
			System.out.println();
			inputStreamReader.close();
			outputStreamWriter.close();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("没有指定文件");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
