package net.oliver.olframework.util.filesystem.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.xml.sax.SAXException;

public class DomManipulateXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* 将XML文件转化成DOM文档 */
		String FileName = "D:\\sample.xml";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse( new File(FileName) );
			
			// 遍历DOM文档 =======================================
			
			//获得Root元素
			Element root = document.getDocumentElement();
			//获得Root元素的子节点列表
			NodeList nodelist = root.getChildNodes();
			//用递归方法实现DOM文档的遍历
			GetElement(nodelist);
			
			// 修改DOM文档 =======================================
			// 在DOM文档中增加一个Element节点
		    Element booktype = document.createElement("COMPUTES");
		    //将该节点转化成root对象的子节点
		    root.appendChild(booktype);
		    //在DOM文档中增加一个Element节点
		    Element booktitle = document.createElement("Title");
		    //将该节点转化成booktype对象的子节点
		    booktype.appendChild(booktitle);
		    //在DOM文档中增加一个Text节点
		    Text bookname = document.createTextNode("understand Corba");
		    //将该节点转化成bookname对象的子节点
		    booktitle.appendChild(bookname);
			
			// 将DOM文档转化成XML文件 ============================
		    // 获得将DOM文档转化为XML文件的转换器，在jdk1.4中，有类TransformerFactory来实现，
		    // 类Transformer实现转化API。
		    TransformerFactory tfactory = TransformerFactory.newInstance();
		    Transformer transformer = tfactory.newTransformer();
		    // 将DOM对象转化为DOMSource类对象，该对象表现为转化成别的表达形式的信息容器。
		    DOMSource source = new DOMSource(document);
		    // 获得一个StreamResult类对象，该对象是DOM文档转化成的其他形式的文档的容器，可以是XML文件，文本文件，HTML文件。这里为一个XML文件。
		    StreamResult result = new StreamResult(new File("D:\\sample2.xml"));
		    // 调用API，将DOM文档转化成XML文件。
		    transformer.transform(source,result);
		    
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	
    public static void GetElement(NodeList nodelist){
		Node cnode;
		int i,en;
		String  str;
		if(nodelist.getLength() == 0){
	        // 该节点没有子节点
	        return;
		}
		for(i=0;i<nodelist.getLength();i++)
		{
			    str= nodelist.item(i).getNodeName();
                System.out.println("      "+str);
        }
    }
}
