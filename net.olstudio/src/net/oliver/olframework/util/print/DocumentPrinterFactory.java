package net.oliver.olframework.util.print;

import net.oliver.olframework.util.print.implement.TXTDocumentPrinter;



// created by mlrain on 2007-6-12 ����08:57:40


/**
 * 
 * Factory for document-printer.
 * 
 * @author mlrain
 * @version 1.0.0, 2007-6-12 ����08:57:43
 */
public class DocumentPrinterFactory
{
    /**
     * ��ȡ�ĵ���ӡ��ʵ�֡�
     * 
     * @return �ĵ���ӡ��ʵ��
     */
    public IDocumentPrinter getPrinter()
    {   
        	IDocumentPrinter printer = new TXTDocumentPrinter();
        	return printer;
    }
}
