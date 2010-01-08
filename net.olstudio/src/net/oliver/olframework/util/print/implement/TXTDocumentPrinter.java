package net.oliver.olframework.util.print.implement;

/*
 * �������� 2007-6-12
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
	
	//�����ҳ��
	private int PAGES = 1;
	//��Ŵ�ӡ�����ַ���
	private String printStr= "";
    //��ӡ����
	private Font font;

	/**
	 *  ��ӡ�ı�����
	 * 
	 * @param doc Ҫ��ӡ���ļ�
	 *  
	 */
	public void printDocument(File doc) throws PrintException {
		
		/*
		String tempfilename = doc.getAbsolutePath();
		//����Ҫ�������������
//		Font font= new Font("����",Font.BOLD,12);
        //�����WORD�ĵ�������ת��ΪTXT�ļ� 
		if(tempfilename.endsWith(".doc")){
            try{   
            	HWPFDocument wd = new HWPFDocument(new FileInputStream(tempfilename));
                Range r = (Range) wd.getRange();
                //����û�лس����ַ���
                printStr = r.text();
                tempfilename +=".txt";
                PrintUtil.saveFile(printStr,tempfilename);
            }catch(Exception eN){   
             eN.printStackTrace();   
            }     
        }
		
		//tempfilenameָ��һ��txt�ĵ�
		try {
			FileReader reader = new FileReader(tempfilename);
			BufferedReader br = new BufferedReader(reader);
			String tempstr;
            while((tempstr = br.readLine()) != null) {
                printStr +="\n";
                printStr +=tempstr;
            }
            //�õ�Ҫ��ӡ��ҳ����
            PAGES = PrintUtil.getPagesCount(printStr);
            //������ʼ�����
            //����ӡ���ݲ�Ϊ��ʱ
            if(printStr != null && printStr.length() > 0){
                //ָ����ӡ�����ʽ
                DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
                //��λĬ�ϵĴ�ӡ����
                PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                DocAttributeSet das = new HashDocAttributeSet();
                DocPrintJob job = svc.createPrintJob();
                //ָ����ӡ����
                Doc printdoc = new SimpleDoc(this,flavor,das);
                //����ʾ��ӡ�Ի���ֱ�ӽ��д�ӡ
                job.print(printdoc, pras);
                //�ر���
                br.close();
                reader.close();
            }else{
            	throw new PrintException("��ӡ����Ϊ�գ�");
            }
           
		} catch (FileNotFoundException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (javax.print.PrintException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}	
		
		*/
	}
    //��дPrintable�ӿڵ�print����
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.black); //���ô�ӡ��ɫΪ��ɫ
        if (pageIndex >= PAGES) //����ӡҳ�Ŵ�����Ҫ��ӡ����ҳ��ʱ����ӡ��������
               return Printable.NO_SUCH_PAGE;
        //ת�����꣬ȷ����ӡ�߽�
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        PrintUtil.drawCurrentPageText(g2, pageFormat, pageIndex,printStr,PAGES,font); //��ӡ��ǰҳ�ı�
        return Printable.PAGE_EXISTS; //���ڴ�ӡҳʱ��������ӡ����
    }
}
