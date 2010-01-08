package net.oliver.olframework.util.print;

import net.oliver.olframework.util.print.implement.TXTDocumentPrinter;



// created by mlrain on 2007-6-12 上午08:57:40


/**
 * 
 * Factory for document-printer.
 * 
 * @author mlrain
 * @version 1.0.0, 2007-6-12 上午08:57:43
 */
public class DocumentPrinterFactory
{
    /**
     * 获取文档打印的实现。
     * 
     * @return 文档打印的实现
     */
    public IDocumentPrinter getPrinter()
    {   
        	IDocumentPrinter printer = new TXTDocumentPrinter();
        	return printer;
    }
}
