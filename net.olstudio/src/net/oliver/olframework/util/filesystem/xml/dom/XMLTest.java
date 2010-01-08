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

import net.oliver.olframework.util.filesystem.html.HTMLDecoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String x= "&lt;![CDATA[14]]&gt;";
		System.out.println(HTMLDecoder.decode(x));
	}
	
	public static void GetElement(NodeList nodelist){
		Node cnode;
		int i,en;
		String  str;
		if(nodelist.getLength() == 0){
	        // 该节点没有子节点
	        return;
		}
		
		
		NodeList nl = nodelist.item(0).getChildNodes();
//		NodeList nl2 = nl.item(0).getChildNodes();
		
		for(i=0;i<nodelist.getLength();i++)
		{
			 NodeList nls = nodelist.item(i).getChildNodes();
			
			 for(int j=0;j<nls.getLength();j++)
			 {
				  str= nls.item(j).getNodeName();
				  if("data".equals(str))
				  {
					  System.out.println("进入");
					  NodeList nll = nls.item(j).getChildNodes();
					  for(int k=0;k<nll.getLength();k++)
					  {
						  Node node = nll.item(k).getFirstChild();
						  System.out.println(node.getNodeName());
//						  System.out.println("field节点长度:"+nll.item(k).getChildNodes().getLength());
////						  String str2= nll.item(k).getNodeName();
//						  System.out.println("      "+str2);
					  }
				  }
	             
			 }
        }
    }

}
