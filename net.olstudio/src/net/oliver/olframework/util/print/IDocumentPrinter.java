package net.oliver.olframework.util.print;

// created by mlrain on 2007-6-12 ����08:50:46

import java.io.File;

/**
 * 
 * �ĵ���ӡ�ӿڡ�
 * 
 * @author mlrain
 * @version 1.0.0, 2007-6-12 ����08:50:57
 */
public interface IDocumentPrinter
{
    /**
     * ��ӡ�ĵ���
     * 
     * @param doc
     *            �ĵ�����
     * @throws PrintException
     *             ��Ҫ��ӡ���ĵ������ڣ����ߴ�ӡ�����г����쳣����֮
     */
    public void printDocument(File doc) throws PrintException;
}
