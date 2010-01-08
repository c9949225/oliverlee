package net.oliver.olframework.util.regex;

/*
 * 中企开源信息技术有限公司
 * 
 * 姓名： 穆朋   联系方式： gavinmupeng@hotmail.com
 * 分机:679       
 **/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//【上海】星光<axiang_2898@126.com> 13:55:09
//"^([+-]?)\\d*\\.?\\d+?$"
//【上海】星光<axiang_2898@126.com> 13:55:45
//上面的就是数字!

public class Test {
    static String s = "<WBCLLB><WBCL><SFBB>0</SFBB><CLMC>asdf</CLMC><CLYS>12</CLYS></WBCL><WBCL><SFBB>0</SFBB><CLMC>sadf</CLMC><CLYS>12</CLYS></WBCL></WBCLLB>";
    
    private static Pattern _pattern = Pattern.compile("(<SFBB>)(\\S{1})(</SFBB>)");
    
    public static void main(String[] args) {
//        Pattern clysPattern = Pattern.compile("<CLYS>[\\d]+</CLYS>");
//        Matcher m = clysPattern.matcher(s);
//        int i =0 ;
//        while (m.find()) {
//            i++;
//            System.out.println(i);
//            System.out.println(m.group());
//        } 
        
    	Matcher m = _pattern.matcher(s);
    	
    	
    	
    	while(m.find())
    	{
    		System.out.println(m.group(2));
    	}
    	
//        Pattern p = Pattern.compile("cat");
//        Matcher m = p.matcher("one cat two cats in the yard");
//        StringBuffer sb = new StringBuffer();
//        while (m.find()) {
//            m.appendReplacement(sb, "dog");
//        }
//        m.appendTail(sb);
//        System.out.println(sb.toString());
    }
}
