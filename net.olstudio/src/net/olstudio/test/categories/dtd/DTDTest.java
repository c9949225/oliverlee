package net.olstudio.test.categories.dtd;

import java.io.File;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;


public class DTDTest {

	public static void main(String[] args) {
		String filePath = "E:\\test.xml";
//		try {
//			InputStream in = new FileInputStream(filePath);
//			SAXReader saxReader = new SAXReader();
//			// ~=====================================================================
//			saxReader.setEntityResolver(new YourDTDResolver());
//			// ~=====================================================================
//			Document document = saxReader.read(in);
//			DocumentBuilderFactory factory = null;
//			factory = DocumentBuilderFactory.newInstance();
//			//进行dtd检查
//			factory.setValidating(true);
//			
//			System.out.println(factory.isValidating());
//			
//			factory.
//		} catch (Exception e) {
//			
//		}
		// ~=====================================================================

		File file = new File(filePath);
        if (file.exists()) {
            SAXBuilder builder = new SAXBuilder();
            builder.setValidation(true);
            builder.setEntityResolver(new YourDTDResolver());
            try {
                Document doc = builder.build(file);
                System.out.println(doc);
            } catch (Exception e) {
            	System.out.println("遇到异常:"+e.getMessage());
            }
        } else {
            System.out.println("can not find xml file:"
                    + file.getAbsolutePath());
        }
	}

}
