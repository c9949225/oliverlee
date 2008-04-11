package net.oliver.olframework.util.filesystem.xml.xmlobject;
/*
 * Created on 2008-1-3 下午02:33:35 by oliverlee
 */

import java.util.List;

public interface IObjectAndXml
{
    /**
     * 将对象列表持久化到一个xml文件中
     * 注意：这里如何替换类的包路径，还需要解决！
     * @param path 输出的xml的路径名
     */
    public void transOut(List list,String path);
    
    /**
     * 从xml文件读取对象返回对象数组
     * 注意：关于检测文件读取结束的方法，暂时使用截获异常的方式，还需要改进
     * @param path 要读取的文件路径名
     */
    public Object[] transIn(String path);

}
