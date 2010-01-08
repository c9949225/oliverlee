package net.oliver.olframework.util.filesystem.html.parse;

import java.io.BufferedReader;
import java.io.FileReader;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;

public class TestHtmlParser {
	
	public static void main(String[] args) throws Exception {

		//Parser parser = new Parser("file://localhost/F:/study/htmlparser/html/1.htm") ;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("html/objects.html");
			br = new BufferedReader(fr);
			StringBuffer html = new StringBuffer();
			String s = br.readLine();
			while (s != null) {
				html.append(s);
				s = br.readLine();
			}

			s = new String(html.toString().getBytes("gb2312"), "8859_1");

			Parser parser = new Parser();
			parser.setInputHTML(s);
			//parser.setEncoding("gb2312");
			NodeFilter textFilter = new NodeClassFilter(TitleTag.class);
			//Get title
			NodeList titles = parser.extractAllNodesThatMatch(textFilter);
	             
//				parser.extractAllNodesThatAre(TitleTag.class);
			TitleTag title = (TitleTag) titles.elementAt(0);
			
			System.out
					.println("title is :"
							+ new String(title.getTitle().getBytes("gb2312"),
									"gb2312"));

			//Reset the parser to start from the beginning again.
			parser.reset();
			//Get <img>; as a Array 
			
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			
//			Node[] images = parser.extractAllNodesThatAre(ImageTag.class);
			NodeList images = parser.extractAllNodesThatMatch(imageFilter);
			
			for(int i=0;i<images.size();i++) 
            { 
				ImageTag imageTag = (ImageTag) images.elementAt(i);
				System.out.println("imagesURl="
						+ new String(imageTag.getImageURL().getBytes(),
								"gb2312"));
            }
			
//			if (images.length != 0) {
//				for (int i = 0; i < images.length; i++) {
//					ImageTag imageTag = (ImageTag) images[i];
//					System.out.println("imagesURl="
//							+ new String(imageTag.getImageURL().getBytes(),
//									"gb2312"));
//				}
//			} else {
//				System.out.println("?amp;ÓÐˆD?amp;!");
//			}

			//Reset the parser to start from the beginning again.
			parser.reset();
			//Get the <table>;
			NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
			NodeList tables = parser.extractAllNodesThatMatch(tableFilter);
			TableTag tableTag = (TableTag) tables.elementAt(0);
			System.out
					.println("tableContent is :"
							+ new String(tableTag.toHtml().getBytes("gb2312"),
									"gb2312"));

		} catch (java.io.IOException ioException) {
			System.out.println(ioException.getMessage());
		} finally {
			fr.close();
			br.close();
		}

	}

}