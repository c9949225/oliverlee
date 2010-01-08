package net.oliver.olframework.util.filesystem.xml.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <DL>
 * <DT><B>Title * . </B></DT>
 * <p>
 * <DD>Description</DD>
 * </DL>
 * <p>
 * 
 * <DL>
 * <DT><B>Usage </B></DT>
 * <p>
 * <DD>Usage Details.</DD>
 * </DL>
 * <p>
 * 
 * @author Steve Pu &ltpu.yun@agree.com.cn&gt;
 * @author Agree Tech Co.
 * @version 1.00, 2005-10-19 10:20:09
 */
public class XmlUtils {

	private static DocumentBuilderFactory factory = DocumentBuilderFactory
			.newInstance();

	// 返回XML跟元素
	public static Element getElementFromReader(Reader reader) throws Exception {
		DocumentBuilder parser;
		try {
			parser = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new Exception("0014 交易画面XML分析错误", e);
		}
		Document doc;
		try {
			doc = parser.parse(new InputSource(reader));
		} catch (SAXException e) {
			throw new Exception("0014 交易画面XML分析错误", e);
		} catch (IOException e) {
			throw new Exception("0014 交易画面XML分析错误", e);
		}
		return doc.getDocumentElement();
	}

	/**
	 * @param stream
	 */
	public static Element getElementFromStream(InputStream stream)
			throws Exception {
		Reader reader = null;
		try {
			reader = new InputStreamReader(stream, "GBK");
		} catch (UnsupportedEncodingException e) {
		}
		return getElementFromReader(reader);
	}

	public static Element[] getChildrenElements(Element parent, String name) {
		List result = new ArrayList();
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getParentNode().equals(parent)
					&& child instanceof Element
					&& name.equals(((Element) child).getNodeName())) {
				result.add(child);
			}
		}
		return (Element[]) result.toArray(new Element[result.size()]);
	}

	// 从xml字符串中获取所有Element
	public static Element getElementFromString(String s) throws Exception {
		Reader reader = new StringReader(s);
		return getElementFromReader(reader);
	}

	private XmlUtils() {
	}
}
