package net.oliver.olframework.util.print;

/*
 * 创建日期 2007-6-12
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.io.FileWriter;

/**
 * @author oliverlee
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class PrintUtil {
	
	 /**
     * 获取打印文件的输出页数
     * 
     * @param printStr 打印字符串内容
     * @return 输出页面数量
     */
    public static int getPagesCount(String printStr)
    {
         int pages = 1;
         int lines = 1;
         int k = 0;
      
         while (printStr.length() > 0) { 
             if (lines < 54) //不够一页时
             {
                 //获取每一个回车符的位置
                 k = printStr.indexOf('\n'); 
                 System.out.println(k);
                 if (k != -1) //存在回车符
                 {
                     lines += 1; //计算行数
                      
                     if (printStr.substring(k + 1).length() > 0) {
                         printStr = printStr.substring(k + 1); //截取尚未打印的文本
                     }
                 
                 }
                 else{
                     break;
                 }
             }
             else{
                 lines =1;
                 pages +=1;
                 
             }
        }
        return pages;
    }
    
    /**
     * 获取指定页面的打印文件的内容
     * 
     * @param g2 图形环境  
     * @param pf 页面格式
     * @param page 指定的页面
     * @param printStr 文件内容
     * @param pages 总页数
     * @param font 打印字体 
     * 
     * @return 当前要打印的页面内容
     */
    public static void drawCurrentPageText(Graphics2D g2, PageFormat pf, int page,String printStr,int pages,Font font) 
    {
         String s = getDrawText(printStr,pages)[page]; 
         //FontRenderContext context = g2.getFontRenderContext();
         Font f = font;
         String drawText;
         float ascent = 16; //给定字符点阵
         int k, i = f.getSize(), lines = 0;
         //待打印文本内容
         while(s.length() > 0 && lines < 54) //每页限定在54行以内
         {
             k = s.indexOf('\n'); //获取每一个回车符的位置
             if (k != -1) //存在回车符
             {
                 lines += 1; //计算行数
                 drawText = s.substring(0, k); //获取每一行文本
                 g2.drawString(drawText, 0, ascent);
                 //具体打印每一行文本，同时走纸移位
                 if (s.substring(k + 1).length() > 0) {
                     s = s.substring(k + 1); //截取尚未打印的文本
                     ascent += i;
                 }  
              }
              else //不存在回车符
              {  lines += 1; //计算行数
                 drawText = s; //获取每一行文本
                 //具体打印每一行文本，同时走纸移位
                 g2.drawString(drawText, 0, ascent); 
                 s = ""; //文本已结束 
              }
         }
      }
      
    /**
     * 将打印目标文本按页存放为字符串数组
     * 
     * @param s 存放打印内容的字符串
     * @param pages 要分成的页面数
     * 
     * @return 存放页面的字符串数组
     */  
    public static String[] getDrawText(String s,int pages) {
        String[] drawText = new String[pages]; //根据页数初始化数组
        for (int i = 0; i < pages; i++)
            drawText[i] = ""; //数组元素初始化为空字符串
            int k, suffix = 0, lines = 0;
            while (s.length() > 0) {
                if (lines < 54) //不够一页时
                {
                    k = s.indexOf('\n');
                    if (k != -1) //存在回车符
                    {
                        lines += 1; //行数累加
                        //计算该页的具体文本内容，存放到相应下标的数组元素
                        drawText[suffix] = drawText[suffix] + s.substring(0, k + 1);
                        if (s.substring(k + 1).length() > 0)
                            s = s.substring(k + 1);
                    }   
                    else
                    {
                        lines += 1; // 行数累加
                        // 将文本内容存放到相应的数组元素
                        drawText[suffix] = drawText[suffix] + s;
                        s = "";
                    }
                }    
                else // 已满一页时
                {
                    lines = 0; //行数统计清零
                    suffix++; //数组下标加1
                }
            }
            return drawText;
    }
    
    
    /**
     * 给定一段字符串保存为TXT文件
     * 
     * @param s 要保存的字符串
     * @param desfilename 目的文件
     * 
     * @return 存放页面的字符串数组
     */ 
    public static void saveFile(String saveStr,String desfilename)
    {
   
        try{    
        FileWriter writer = new FileWriter(desfilename);   
        int saveStrLen = saveStr.length();   
        for(int i=0; i<saveStrLen; i++)   
        writer.write((int)saveStr.charAt(i));   
        writer.close();   
        }catch(Exception eF){   
        eF.printStackTrace();   
        }   //   end   for   catch    
        
    }   

}
