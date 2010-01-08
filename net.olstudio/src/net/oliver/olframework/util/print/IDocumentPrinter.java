package net.oliver.olframework.util.print;

// created by mlrain on 2007-6-12 上午08:50:46

import java.io.File;

/**
 * 
 * 文档打印接口。
 * 
 * @author mlrain
 * @version 1.0.0, 2007-6-12 上午08:50:57
 */
public interface IDocumentPrinter
{
    /**
     * 打印文档。
     * 
     * @param doc
     *            文档对象
     * @throws PrintException
     *             需要打印的文档不存在，或者打印过程中出现异常则抛之
     */
    public void printDocument(File doc) throws PrintException;
}
