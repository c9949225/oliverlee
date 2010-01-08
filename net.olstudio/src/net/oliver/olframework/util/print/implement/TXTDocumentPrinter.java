package net.oliver.olframework.util.print.implement;

/*
 * 创建日期 2007-6-12
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import net.oliver.olframework.util.print.IDocumentPrinter;
import net.oliver.olframework.util.print.PrintException;
import net.oliver.olframework.util.print.PrintUtil;

import org.apache.commons.lang.math.Range;

public class TXTDocumentPrinter implements IDocumentPrinter,Printable {
	
	//存放总页数
	private int PAGES = 1;
	//存放打印内容字符串
	private String printStr= "";
    //打印字体
	private Font font;

	/**
	 *  打印文本内容
	 * 
	 * @param doc 要打印的文件
	 *  
	 */
	public void printDocument(File doc) throws PrintException {
		
		/*
		String tempfilename = doc.getAbsolutePath();
		//设置要打输出的字体风格
//		Font font= new Font("宋体",Font.BOLD,12);
        //如果是WORD文档，将其转换为TXT文件 
		if(tempfilename.endsWith(".doc")){
            try{   
            	HWPFDocument wd = new HWPFDocument(new FileInputStream(tempfilename));
                Range r = (Range) wd.getRange();
                //读到没有回车的字符串
                printStr = r.text();
                tempfilename +=".txt";
                PrintUtil.saveFile(printStr,tempfilename);
            }catch(Exception eN){   
             eN.printStackTrace();   
            }     
        }
		
		//tempfilename指向一个txt文档
		try {
			FileReader reader = new FileReader(tempfilename);
			BufferedReader br = new BufferedReader(reader);
			String tempstr;
            while((tempstr = br.readLine()) != null) {
                printStr +="\n";
                printStr +=tempstr;
            }
            //得到要打印的页面数
            PAGES = PrintUtil.getPagesCount(printStr);
            //变量初始化完毕
            //当打印内容不为空时
            if(printStr != null && printStr.length() > 0){
                //指定打印输出格式
                DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
                //定位默认的打印服务
                PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                DocAttributeSet das = new HashDocAttributeSet();
                DocPrintJob job = svc.createPrintJob();
                //指定打印内容
                Doc printdoc = new SimpleDoc(this,flavor,das);
                //不显示打印对话框，直接进行打印
                job.print(printdoc, pras);
                //关闭流
                br.close();
                reader.close();
            }else{
            	throw new PrintException("打印内容为空！");
            }
           
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (javax.print.PrintException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}	
		
		*/
	}
    //重写Printable接口的print方法
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.black); //设置打印颜色为黑色
        if (pageIndex >= PAGES) //当打印页号大于需要打印的总页数时，打印工作结束
               return Printable.NO_SUCH_PAGE;
        //转换坐标，确定打印边界
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        PrintUtil.drawCurrentPageText(g2, pageFormat, pageIndex,printStr,PAGES,font); //打印当前页文本
        return Printable.PAGE_EXISTS; //存在打印页时，继续打印工作
    }
}
