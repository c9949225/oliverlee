package net.oliver.olframework.util.filesystem.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*�� ҵ ְ �� �� ʼ �� ��


��ҵ�ͻ���: 0000012554                             ��ҵ����: �Ϻ�������������          

ҵ�������: cjkf500420080801000015                                   


+------+------------+---------+------------+--------------------+------+
| ��� | ���˿ͻ��� |   ����  |  ֤������  |      ֤�����      | ���� |
+------+------------+---------+------------+--------------------+------+
|     1|1000149359  |��֮��   |���֤      |310110197710215026  |974058|
+------+------------+---------+------------+--------------------+------+
|     2|1000150799  |���ȴ�   |���֤      |310104197802020843  |240746|
+------+------------+---------+------------+--------------------+------+
|     3|1000149258  |����     |���֤      |310108196304282841  |107703|
+------+------------+---------+------------+--------------------+------+
|     4|1000148936  |Ȩӱ��   |���֤      |120104196811306325  |845701|
+------+------------+---------+------------+--------------------+------+*/

/**
 * ��ȡ����������ֶ�
 */
public class ReadFileLineByLine {

	// �����Ŵﵽ6λ�Ļ����ͻ��ҵ���Ŷ����ǿͻ���
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
			// ����Ҳ���ȡ����ȡ��nullֻ�Ǳ����������ļ���β
			while ((line = bufReader.readLine()) != null) {
				System.out.println("��ȡ��:"+ line);
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
