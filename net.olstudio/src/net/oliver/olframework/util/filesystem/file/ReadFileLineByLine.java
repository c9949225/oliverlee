package net.oliver.olframework.util.filesystem.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*企 业 职 工 初 始 密 码


企业客户号: 0000012554                             企业名称: 上海锦江青年会宾馆          

业务受理号: cjkf500420080801000015                                   


+------+------------+---------+------------+--------------------+------+
| 序号 | 个人客户号 |   姓名  |  证件类型  |      证件编号      | 密码 |
+------+------------+---------+------------+--------------------+------+
|     1|1000149359  |夏之音   |身份证      |310110197710215026  |974058|
+------+------------+---------+------------+--------------------+------+
|     2|1000150799  |张奕春   |身份证      |310104197802020843  |240746|
+------+------------+---------+------------+--------------------+------+
|     3|1000149258  |季虹     |身份证      |310108196304282841  |107703|
+------+------------+---------+------------+--------------------+------+
|     4|1000148936  |权颖兰   |身份证      |120104196811306325  |845701|
+------+------------+---------+------------+--------------------+------+*/

/**
 * 读取上面的密码字段
 */
public class ReadFileLineByLine {

	// 如果序号达到6位的话，就会找到序号而不是客户号
    private final static Pattern password_pattern = Pattern.compile("[|]\\d{6}[|]"); 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String srcpath = "D:\\test.txt";
		 try {
			Reader reader = new FileReader(srcpath);
			BufferedReader bufReader = new BufferedReader(reader);
			String line = null;
			Matcher m = null;
			// 空行也会读取，读取到null只是标明读到了文件结尾
			while ((line = bufReader.readLine()) != null) {
				System.out.println("读取到:"+ line);
//				m = password_pattern.matcher(line);
//				while (m.find()) {
//					System.out.println(m.group().substring(1,7));
//				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
