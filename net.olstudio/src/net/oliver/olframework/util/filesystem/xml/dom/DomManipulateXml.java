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
		
		/* ��XML�ļ�ת����DOM�ĵ� */
		String FileName = "D:\\sample.xml";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse( new File(FileName) );
			
			// ����DOM�ĵ� =======================================
			
			//���RootԪ��
			Element root = document.getDocumentElement();
			//���RootԪ�ص��ӽڵ��б�
			NodeList nodelist = root.getChildNodes();
			//�õݹ鷽��ʵ��DOM�ĵ��ı���
			GetElement(nodelist);
			
			// �޸�DOM�ĵ� =======================================
			// ��DOM�ĵ�������һ��Element�ڵ�
		    Element booktype = document.createElement("COMPUTES");
		    //���ýڵ�ת����root������ӽڵ�
		    root.appendChild(booktype);
		    //��DOM�ĵ�������һ��Element�ڵ�
		    Element booktitle = document.createElement("Title");
		    //���ýڵ�ת����booktype������ӽڵ�
		    booktype.appendChild(booktitle);
		    //��DOM�ĵ�������һ��Text�ڵ�
		    Text bookname = document.createTextNode("understand Corba");
		    //���ýڵ�ת����bookname������ӽڵ�
		    booktitle.appendChild(bookname);
			
			// ��DOM�ĵ�ת����XML�ļ� ============================
		    // ��ý�DOM�ĵ�ת��ΪXML�ļ���ת��������jdk1.4�У�����TransformerFactory��ʵ�֣�
		    // ��Transformerʵ��ת��API��
		    TransformerFactory tfactory = TransformerFactory.newInstance();
		    Transformer transformer = tfactory.newTransformer();
		    // ��DOM����ת��ΪDOMSource����󣬸ö������Ϊת���ɱ�ı����ʽ����Ϣ������
		    DOMSource source = new DOMSource(document);
		    // ���һ��StreamResult����󣬸ö�����DOM�ĵ�ת���ɵ�������ʽ���ĵ���������������XML�ļ����ı��ļ���HTML�ļ�������Ϊһ��XML�ļ���
		    StreamResult result = new StreamResult(new File("D:\\sample2.xml"));
		    // ����API����DOM�ĵ�ת����XML�ļ���
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
	        // �ýڵ�û���ӽڵ�
	        return;
		}
		for(i=0;i<nodelist.getLength();i++)
		{
			    str= nodelist.item(i).getNodeName();
                System.out.println("      "+str);
        }
    }
}
