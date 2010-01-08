package net.oliver.olframework.util.filesystem.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将Linux文本转换为Dos格式
 * \r 13
 * \n 10
 * int类型和char类型是通用的都可以表示一个字符的字节表示
 */
/*if(flag){// 若是下载错误清单
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
					// 写入"\r\n"
					fileWriter.write(wlnChar);
				}
				else
					fileWriter.write(in);
			}
			fileReader.close();
			fileWriter.close();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("请指定文件");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
