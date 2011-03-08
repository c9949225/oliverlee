package net.oliver.olframework.util.string;

import java.io.File;
import java.io.IOException;

//Pattern pattern = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
//Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\d{3}\\-\\d{4}");
public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		/*File file = new File("C:/wsdl/HelloGspService/client/x.txt");
		System.out.println(file.getCanonicalPath());
		String x = file.getCanonicalPath().replaceAll("[/|\\\\]", ".");
		System.out.println(x .substring(x.indexOf("client.x")));*/
		String x = "00000";
		System.out.println(x.substring(3));
		  /*  BufferedReader in;

		    Map display_var = new LinkedHashMap();
		    Map symbol_var = new LinkedHashMap();
		    //找到所有的画画面 @ 8,3 SAY "借记卡卡号    [                   ]"
//		    Pattern pattern = Pattern.compile("\\s*@\\s*\\d+,\\d+\\s*SAY\\s*\"[\u0391-\uFFE5]+\\s*\\[\\s*\\].*");
		    
		    //找到所有的函数 FUNC Print_Private ()
//		    Pattern pattern = Pattern.compile("\\s*FUNC\\s*\\w+\\s*()");

		    //找到所有ONBLUR [FUNC Query ()]
//		    Pattern pattern = Pattern.compile("\\s*ONBLUR\\s*\\[\\s*FUNC+\\s*\\w+\\s*\\(\\)\\s*\\]");
		    
		    // @ 5,18 GET (sKR_RBANK_SEQ) PICTURE -11"X"
//		    Pattern pattern2 = Pattern.compile("\\s*@\\s*\\d+,\\d+\\s*GET\\s*\\(*\\w+\\)*\\s*");
		    
		    Pattern pattern = Pattern.compile("\\s*FUNC\\s*\\w+\\s*\\(\\)");
		    try {
				  in = new BufferedReader(new FileReader("D:\\test.txt"));
				  String s;
				  int i=0;
				  while ((s = in.readLine()) != null){
					    //Matcher matcher = pattern.matcher
				    	Matcher matcher = pattern.matcher(s);
//				    	Matcher matcher2 = pattern2.matcher(s);
				    	String varline = "";
				    	if(matcher.matches()){
//				    		String var = matcher.group();
//				    		varline = String.valueOf(i);
//				    		display_var.put(varline, var.substring(var.indexOf("\"")+1,var.indexOf("[")).trim());
				    		System.out.print("行："+i);
				    		System.out.println(matcher.group());
				    	}
				    	
//				    	if(matcher2.find()){
//				    		System.out.println(varline);
//				    		String var = matcher2.group();
//				    		if(var.indexOf("(")>0){
//				    			symbol_var.put(varline, var.substring(var.indexOf("(")+1,var.indexOf(")")).trim());
//				    		}else{
//				    			symbol_var.put(varline, var.substring(var.indexOf("GET")+3).trim());
//				    		}
//				    	}
				    	i+=1;
				    }
				  in.close();
				    
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
//			Iterator it = display_var.entrySet().iterator();
//
//			while(it.hasNext()){
//				Entry entry = (Entry) it.next();
//				String value = (String)entry.getValue();
//				System.out.println("第"+(String)entry.getKey()+"行："+value);
//				System.out.println("变量名"+(String)symbol_var.get((String)entry.getKey()));
//			}
*/	}

}
