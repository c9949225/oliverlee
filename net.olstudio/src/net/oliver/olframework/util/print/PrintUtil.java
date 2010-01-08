package net.oliver.olframework.util.print;

/*
 * �������� 2007-6-12
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.io.FileWriter;

/**
 * @author oliverlee
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class PrintUtil {
	
	 /**
     * ��ȡ��ӡ�ļ������ҳ��
     * 
     * @param printStr ��ӡ�ַ�������
     * @return ���ҳ������
     */
    public static int getPagesCount(String printStr)
    {
         int pages = 1;
         int lines = 1;
         int k = 0;
      
         while (printStr.length() > 0) { 
             if (lines < 54) //����һҳʱ
             {
                 //��ȡÿһ���س�����λ��
                 k = printStr.indexOf('\n'); 
                 System.out.println(k);
                 if (k != -1) //���ڻس���
                 {
                     lines += 1; //��������
                      
                     if (printStr.substring(k + 1).length() > 0) {
                         printStr = printStr.substring(k + 1); //��ȡ��δ��ӡ���ı�
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
     * ��ȡָ��ҳ��Ĵ�ӡ�ļ�������
     * 
     * @param g2 ͼ�λ���  
     * @param pf ҳ���ʽ
     * @param page ָ����ҳ��
     * @param printStr �ļ�����
     * @param pages ��ҳ��
     * @param font ��ӡ���� 
     * 
     * @return ��ǰҪ��ӡ��ҳ������
     */
    public static void drawCurrentPageText(Graphics2D g2, PageFormat pf, int page,String printStr,int pages,Font font) 
    {
         String s = getDrawText(printStr,pages)[page]; 
         //FontRenderContext context = g2.getFontRenderContext();
         Font f = font;
         String drawText;
         float ascent = 16; //�����ַ�����
         int k, i = f.getSize(), lines = 0;
         //����ӡ�ı�����
         while(s.length() > 0 && lines < 54) //ÿҳ�޶���54������
         {
             k = s.indexOf('\n'); //��ȡÿһ���س�����λ��
             if (k != -1) //���ڻس���
             {
                 lines += 1; //��������
                 drawText = s.substring(0, k); //��ȡÿһ���ı�
                 g2.drawString(drawText, 0, ascent);
                 //�����ӡÿһ���ı���ͬʱ��ֽ��λ
                 if (s.substring(k + 1).length() > 0) {
                     s = s.substring(k + 1); //��ȡ��δ��ӡ���ı�
                     ascent += i;
                 }  
              }
              else //�����ڻس���
              {  lines += 1; //��������
                 drawText = s; //��ȡÿһ���ı�
                 //�����ӡÿһ���ı���ͬʱ��ֽ��λ
                 g2.drawString(drawText, 0, ascent); 
                 s = ""; //�ı��ѽ��� 
              }
         }
      }
      
    /**
     * ����ӡĿ���ı���ҳ���Ϊ�ַ�������
     * 
     * @param s ��Ŵ�ӡ���ݵ��ַ���
     * @param pages Ҫ�ֳɵ�ҳ����
     * 
     * @return ���ҳ����ַ�������
     */  
    public static String[] getDrawText(String s,int pages) {
        String[] drawText = new String[pages]; //����ҳ����ʼ������
        for (int i = 0; i < pages; i++)
            drawText[i] = ""; //����Ԫ�س�ʼ��Ϊ���ַ���
            int k, suffix = 0, lines = 0;
            while (s.length() > 0) {
                if (lines < 54) //����һҳʱ
                {
                    k = s.indexOf('\n');
                    if (k != -1) //���ڻس���
                    {
                        lines += 1; //�����ۼ�
                        //�����ҳ�ľ����ı����ݣ���ŵ���Ӧ�±������Ԫ��
                        drawText[suffix] = drawText[suffix] + s.substring(0, k + 1);
                        if (s.substring(k + 1).length() > 0)
                            s = s.substring(k + 1);
                    }   
                    else
                    {
                        lines += 1; // �����ۼ�
                        // ���ı����ݴ�ŵ���Ӧ������Ԫ��
                        drawText[suffix] = drawText[suffix] + s;
                        s = "";
                    }
                }    
                else // ����һҳʱ
                {
                    lines = 0; //����ͳ������
                    suffix++; //�����±��1
                }
            }
            return drawText;
    }
    
    
    /**
     * ����һ���ַ�������ΪTXT�ļ�
     * 
     * @param s Ҫ������ַ���
     * @param desfilename Ŀ���ļ�
     * 
     * @return ���ҳ����ַ�������
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
