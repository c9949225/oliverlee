package net.oliver.olframework.util.filesystem.xml.xmlobject;
/*
 * Created on 2008-1-3 ����02:33:35 by oliverlee
 */

import java.util.List;

public interface IObjectAndXml
{
    /**
     * �������б�־û���һ��xml�ļ���
     * ע�⣺��������滻��İ�·��������Ҫ�����
     * @param path �����xml��·����
     */
    public void transOut(List list,String path);
    
    /**
     * ��xml�ļ���ȡ���󷵻ض�������
     * ע�⣺���ڼ���ļ���ȡ�����ķ�������ʱʹ�ýػ��쳣�ķ�ʽ������Ҫ�Ľ�
     * @param path Ҫ��ȡ���ļ�·����
     */
    public Object[] transIn(String path);

}
