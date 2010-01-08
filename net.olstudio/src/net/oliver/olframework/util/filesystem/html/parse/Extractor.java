package net.oliver.olframework.util.filesystem.html.parse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;


public class Extractor { 
	
	private Parser getParser()
	{
		return Parser.createParser("", "GBK");
	}
	
	private String getOutputPath()
	{
		return "";
	}
	
	private String getInputFilePath()
	{
		return "";
	}
	
	public static final String NEWLINE = "\r\n";
	
    public void extract() 
    { 
        BufferedWriter bw=null; 
        String indextime=""; 
        String title=""; 
        StringBuffer body=new StringBuffer(); 
        
        NodeFilter time_filter=new OrFilter(
        		new AndFilter(
        		new TagNameFilter("h2"),
        		new HasAttributeFilter("class","ContentAuthor")), // classΪContentAuthor��h2��ǩ 
        		// ����
                new AndFilter(
                new TagNameFilter("font"), // ��ɫΪ#808080��font��ǩ
                new HasAttributeFilter("color","#808080")
       )); 
         
        NodeFilter title_filter1=new OrFilter(
        		new AndFilter(
        				new TagNameFilter("td"),
        				new HasChildFilter(new TagNameFilter("b"))), // ��b��ǩ��td
        				
                new AndFilter(
                		new TagNameFilter("h1"),
                		new HasChildFilter(new TagNameFilter("strong"))));// h1������strong�ı���
        		
        //new AndFilter(new HasAttributeFilter("color","#000080") 
         
        NodeFilter  body_filter=new OrFilter(
        		new AndFilter(
        				new TagNameFilter("div"),
        				new HasAttributeFilter("id","logPanel")), // id��logPanel��div
        		
                new AndFilter(
                		new TagNameFilter("td"),
                		new HasChildFilter(new TagNameFilter("p"))));// ��p��td 
         
        try 
        { 
            NodeList title_nodes=this.getParser().parse(title_filter1); 
            //�������нڵ� 
             
               /* 
                * ȡ�÷��Ϲ��������ĵ�һ����� 
                */ 
                Node node=title_nodes.elementAt(0); 
                /* 
                 * �ѷ���������tag֮������к��ӽڵ�����б� 
                 */ 
                NodeList node2=node.getChildren();  
                 
                //title=node2.elementAt(0).toHtml(); 
                /* 
                 * ���ͽ�����ȡֵ 
                 */ 
                title=node2.elementAt(1).toHtml();    
               // title=node2.elementAt(2).toHtml();  
                /* 
                 * ��ҳ���� 
                 */ 
                if(title.indexOf("<")!=-1) 
                	title=node2.elementAt(3).toHtml();    

            /* 
             * д���ļ��� 
             */ 
            bw=new BufferedWriter(new FileWriter(new File(this.getOutputPath()+title+".txt"))); 
             
            int end1 = getInputFilePath().lastIndexOf("i"); 
            int end2 = getInputFilePath().lastIndexOf("."); 
            
            String url_seg1=getInputFilePath().substring(3,end1); 
            String url_seg2=getInputFilePath().substring(end1, end2); 
            String url_seg=url_seg1+".asp?"+url_seg2; 
            url_seg=url_seg.replaceAll("\\\\","/"); 
            String url="http://"+url_seg; 
             
            bw.write(url+NEWLINE); 
            bw.write(title+NEWLINE); 
             
         
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        
        // ����
        this.getParser().reset(); 
        
        try 
        { 
            NodeList time_nodes=this.getParser().parse(time_filter); 
            
            for(int i=0;i<time_nodes.size();i++) 
            { 
               /* 
                * ��̫�ã��д��Ľ��� 
                */ 
                Node time_node=time_nodes.elementAt(i); 
                indextime=time_node.getNextSibling().toHtml(); 
                 
                /* 
                Node node=time_nodes.elementAt(i);  
                NodeList node1=node.getChildren();  
                indextime=node1.elementAt(0).toHtml(); 
                */ 
                /* 
                 * ��Node�࣬���������getChildren�� 
                 * ����tag�࣬����ԡ� 
                 */ 
                if(indextime.indexOf("����")!=-1) 
                { 
                    indextime=indextime.substring(indextime.lastIndexOf(":")+1); 
                } 
            } 
             
            bw.write(indextime+NEWLINE); 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        
        // ����
        this.getParser().reset(); 
        
        try 
        { 
            NodeList body_nodes=this.getParser().parse(body_filter); 
            
            for(int i=0;i<body_nodes.size();i++) 
            { 
                Node node=body_nodes.elementAt(i); 
                 
                Parser body_parser=new Parser(node.toHtml()); 
                // ��ȡ�����е�����
                TextExtractingVisitor visitor=new TextExtractingVisitor(); 
                body_parser.visitAllNodesWith(visitor); 
                
                body.append(visitor.getExtractedText()); 
            } 
            
            String text=body.toString(); 
            String newtext=""; 
            /*while(text.indexOf("<")!=-1) 
            { 
                text=text.substring(0, body.indexOf("<")); 
                text+=text.substring(75); 
            } 
            */ 
            /* 
             * ѭ�����е��ʱ�ˡ� 
             * ��Ҫ��Ϊ�˳�ȥ�ı��� 
             * <?xml:namespace prefix = o ns = "urn:schemas-microsoft-com:office:office" /> 
             * �ֶΡ�˳���һЩû��Ҫ�Ŀո�ȥ���� 
             */ 
            for(int i=0;i<text.length();i++) 
            { 
                char ch=text.charAt(i); 
                if(ch=='<') 
                { 
                    i++; 
                    while(text.charAt(i)!='>') 
                        i++; 
                } 
                 
                if(ch!='<'&&ch!=' ') 
                newtext+=ch; 
            } 
             
            bw.write(newtext+NEWLINE); 
         
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
         
        try 
        { 
            if(bw!=null) 
                bw.close(); 
        }catch(IOException e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}