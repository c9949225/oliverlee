package net.oliver.olframework.util.filesystem.html.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;
import org.htmlparser.visitors.TextExtractingVisitor;

//import com.jscud.util.LogMan; //һ����־��¼��

/**
 * ��ʾ��Html Parse��Ӧ��.
 * 
 * @author scud http://www.jscud.com
 */

public class ParseHtmlTest {

	public static void main(String[] args) throws Exception {
		String aFile = "e:/jscud/temp/test.htm";

		String content = readTextFile(aFile, "GBK");

//		test1(content);
//		System.out.println("====================================");

		test2(content);
		System.out.println("====================================");

		test3(content);
		System.out.println("====================================");

		test4(content);
		System.out.println("====================================");

		test5(aFile);
		System.out.println("====================================");

		// �����ⲿ��Դ,�����
		test5("http://www.jscud.com");
		System.out.println("====================================");

	}

	/**
	 * ��ȡ�ļ��ķ�ʽ����������. filePathҲ������һ��Url.
	 * 
	 * @param resource
	 *            �ļ�/Url
	 */
	public static void test5(String resource) throws Exception {
		Parser myParser = new Parser(resource);

		// ���ñ���
		myParser.setEncoding("GBK");

		HtmlPage visitor = new HtmlPage(myParser);

		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getTitle();

		System.out.println(textInPage);
	}

	/**
	 * ��ҳ�淽ʽ����.��һ����׼��Htmlҳ��,�Ƽ�ʹ�ô��ַ�ʽ.
	 */
	public static void test4(String content) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(content, "GBK");

		HtmlPage visitor = new HtmlPage(myParser);

		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getTitle();

		System.out.println(textInPage);
	}

	/**
	 * ����Visitorģʽ����htmlҳ��.
	 *
	 * С�ŵ�:������<>�ȷ��� 
	 * ȱ��:�ö�ո�,�޷���ȡlink
	 *   
	 */
	public static void test3(String content) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(content, "GBK");

		TextExtractingVisitor visitor = new TextExtractingVisitor();

		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getExtractedText();

		System.out.println(textInPage);
	}

	/**
	 * �õ���ͨ�ı������ӵ�����.
	 * 
	 * ʹ���˹�������.
	 */
	public static void test2(String content) throws ParserException {
		Parser myParser;
		NodeList nodeList = null;

		myParser = Parser.createParser(content, "GBK");

		NodeFilter textFilter = new NodeClassFilter(TextNode.class);
		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);

		//��ʱ������ meta
		//NodeFilter metaFilter = new NodeClassFilter(MetaTag.class);

		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { textFilter, linkFilter });

		nodeList = myParser.parse(lastFilter);

		Node[] nodes = nodeList.toNodeArray();

		for (int i = 0; i < nodes.length; i++) {
			Node anode = (Node) nodes[i];

			String line = "";
			if (anode instanceof TextNode) {
				TextNode textnode = (TextNode) anode;
				//line = textnode.toPlainTextString().trim();
				line = textnode.getText();
			} else if (anode instanceof LinkTag) {
				LinkTag linknode = (LinkTag) anode;

				line = linknode.getLink();
				//@todo ����jsp��ǩ:�����Լ�ʵ���������
				//line = StringFunc.replace(line, "<%.*%>", "");
			}

			if (isTrimEmpty(line))
				continue;

			System.out.println(line);
		}
	}

	/**
	 * ������ͨ�ı��ڵ�.
	 * 
	 * @param content
	 * @throws ParserException
	 */
//	public static void test1(String content) throws ParserException {
//		Parser myParser;
//		Node[] nodes = null;
//
//		myParser = Parser.createParser(content, null);
//
//		nodes = myParser.extractAllNodesThatAre(TextNode.class); //exception could be thrown here
//
//		for (int i = 0; i < nodes.length; i++) {
//			TextNode textnode = (TextNode) nodes[i];
//			String line = textnode.toPlainTextString().trim();
//			if (line.equals(""))
//				continue;
//			System.out.println(line);
//		}
//
//	}

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

	/**
	 * ȥ�����ҿո���ַ����Ƿ�Ϊ��
	 * @param astr String
	 * @return boolean
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(astr.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * �ַ����Ƿ�Ϊ��:null���߳���Ϊ0.
	 * @param astr Դ�ַ���.
	 * @return boolean
	 */
	public static boolean isBlank(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
    
	private Object[] extractText(String html, String identifier)
			throws Exception {
		List<String> resultTextList = new ArrayList<String>();

		Parser parser = new Parser();
		parser.setInputHTML(html);

		NodeFilter filter = new HasAttributeFilter("class", identifier);
		/* NodeFilter filter ����Ҫ�����Ĺ�������ʵ���кö��֣��Ҳ��õ����Թ���,����more api*/

		NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		/* extractAllNodesThatAre(class)�Ѿ������Ƽ�ʹ�ã���1.6�汾�У��Ҹе����������������.���õ��������Զ����tag */
		if (nodeList == null)
			return null;
		if (nodeList.size() == 0)
			return null;

		// System.out.println("start ============== ,size = "
		// + nodeList.size());
		Node[] nodes = nodeList.toNodeArray();
		String line = "";
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i]; /*�õ����Է��ϵĽڵ㣬���ͻ�����Ӧ�ı�ǩ��*/
			if (node instanceof Span) {
				Span spanTag = (Span) node;
				line = spanTag.toPlainTextString();
			} else if (node instanceof TableColumn) {
				TableColumn tableTag = (TableColumn) node;
				line = tableTag.toPlainTextString();
			} else if (node instanceof Div) {
				Div divTag = (Div) node;
				line = divTag.toPlainTextString();
			}
			if (isTrimEmpty(line)) {
				continue;
			} else {
				resultTextList.add(line);
			}

		}
		return resultTextList.toArray();
	}

}
