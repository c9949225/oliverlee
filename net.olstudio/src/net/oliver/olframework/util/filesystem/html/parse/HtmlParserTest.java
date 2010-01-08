package net.oliver.olframework.util.filesystem.html.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTest {

	public static final String SRC = "D:\\agree\\msb\\svn\\�ĵ�\\ҵ������\\����\\���\\������ծ\\������ծ��Ϣ.HTML";
	
	/**
	 * ��ȡһ���ļ����ַ�����.
	 * 
	 * @param sFileName  �ļ���
	 * @param sEncode   String
	 * @return �ļ�����
	 */
	public static String readTextFile(String sFileName, String sEncode) {
		StringBuffer sbStr = new StringBuffer();

		try {
			File ff = new File(sFileName);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					ff), sEncode);
			BufferedReader ins = new BufferedReader(read);

			String dataLine = "";
			while (null != (dataLine = ins.readLine())) {
				sbStr.append(dataLine);
				sbStr.append("\r\n");
			}

			ins.close();
		} catch (Exception e) {
			System.out.println("read Text File Error"+ e);
		}

		return sbStr.toString();
	}
	
	public static Parser getParser()
	{
		return Parser.createParser(readTextFile(SRC, "GBK"),"GBK");
	}
	
	/**
	 * �õ�����
	 * @return
	 */
	public static String getTitle()
	{
		NodeFilter titleFilter = new TagNameFilter("title");
		try {
			NodeList title_nodes= getParser().parse(titleFilter);
			if(title_nodes.size()>0)
				return title_nodes.elementAt(0).toPlainTextString();
		} catch (ParserException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	/**
	 * �õ���ǰλ�ñ�ǩ
	 * 
	 * @return
	 */
	public static String getPosition()
	{
		 NodeFilter  imageFilter=
	        		new AndFilter(
	        				new TagNameFilter("img"),
	        				new HasAttributeFilter("src","/webroot/images/location_arrow.gif")
	        				);
		 try {
				NodeList title_nodes= getParser().parse(imageFilter);
				if(title_nodes.size()>0)
					return title_nodes.elementAt(0).getNextSibling().toPlainTextString();
			} catch (ParserException e) {
				e.printStackTrace();
			} 
			return "";
	}
	
	public static void getRecordTables()
	{
		NodeFilter aFilter = new  AndFilter(
						new TagNameFilter("a"),
						new HasAttributeFilter("name","format")
				
		);
		
		try {
			NodeList a_nodes= getParser().parse(aFilter);
			
			for(int i=0;i<a_nodes.size();i++)
			{
				System.out.println(a_nodes.elementAt(i).toPlainTextString());
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		getRecordTables();
	}
}
