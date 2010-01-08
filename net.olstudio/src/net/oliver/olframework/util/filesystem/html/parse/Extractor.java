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
        		new HasAttributeFilter("class","ContentAuthor")), // class为ContentAuthor的h2标签 
        		// 或者
                new AndFilter(
                new TagNameFilter("font"), // 颜色为#808080的font标签
                new HasAttributeFilter("color","#808080")
       )); 
         
        NodeFilter title_filter1=new OrFilter(
        		new AndFilter(
        				new TagNameFilter("td"),
        				new HasChildFilter(new TagNameFilter("b"))), // 有b标签的td
        				
                new AndFilter(
                		new TagNameFilter("h1"),
                		new HasChildFilter(new TagNameFilter("strong"))));// h1里面有strong的表情
        		
        //new AndFilter(new HasAttributeFilter("color","#000080") 
         
        NodeFilter  body_filter=new OrFilter(
        		new AndFilter(
        				new TagNameFilter("div"),
        				new HasAttributeFilter("id","logPanel")), // id叫logPanel的div
        		
                new AndFilter(
                		new TagNameFilter("td"),
                		new HasChildFilter(new TagNameFilter("p"))));// 有p的td 
         
        try 
        { 
            NodeList title_nodes=this.getParser().parse(title_filter1); 
            //遍历所有节点 
             
               /* 
                * 取得符合过滤条件的第一个结果 
                */ 
                Node node=title_nodes.elementAt(0); 
                /* 
                 * 把符合条件的tag之间的所有孩子节点进行列表。 
                 */ 
                NodeList node2=node.getChildren();  
                 
                //title=node2.elementAt(0).toHtml(); 
                /* 
                 * 博客解析，取值 
                 */ 
                title=node2.elementAt(1).toHtml();    
               // title=node2.elementAt(2).toHtml();  
                /* 
                 * 主页解析 
                 */ 
                if(title.indexOf("<")!=-1) 
                	title=node2.elementAt(3).toHtml();    

            /* 
             * 写入文件。 
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
        
        // 重置
        this.getParser().reset(); 
        
        try 
        { 
            NodeList time_nodes=this.getParser().parse(time_filter); 
            
            for(int i=0;i<time_nodes.size();i++) 
            { 
               /* 
                * 不太好，有待改进。 
                */ 
                Node time_node=time_nodes.elementAt(i); 
                indextime=time_node.getNextSibling().toHtml(); 
                 
                /* 
                Node node=time_nodes.elementAt(i);  
                NodeList node1=node.getChildren();  
                indextime=node1.elementAt(0).toHtml(); 
                */ 
                /* 
                 * 用Node类，其对象不能用getChildren。 
                 * 而用tag类，则可以。 
                 */ 
                if(indextime.indexOf("日期")!=-1) 
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
        
        // 重置
        this.getParser().reset(); 
        
        try 
        { 
            NodeList body_nodes=this.getParser().parse(body_filter); 
            
            for(int i=0;i<body_nodes.size();i++) 
            { 
                Node node=body_nodes.elementAt(i); 
                 
                Parser body_parser=new Parser(node.toHtml()); 
                // 获取容器中的内容
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
             * 循环，有点耗时了。 
             * 主要是为了除去文本中 
             * <?xml:namespace prefix = o ns = "urn:schemas-microsoft-com:office:office" /> 
             * 字段。顺便把一些没必要的空格去掉。 
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