package net.oliver.olframework.util.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


//Log4j�������أ���������������
//Ӧ��ע�⵽���������������Դ���룬��ȫû���漰��Log4j����������������ϣ���ģ���Ҳ����commons-logging��Ҫ�ﵽ��Ŀ��֮һ��
//���ǣ���ô������Log4j�������������أ��𰸺ܼ򵥣�ֻ�����㡰classpath����Log4j��jar������
//ǰ���Ѿ�˵���ˣ�commons-logging���Զ����ֲ�Ӧ��Log4j��
//����ֻҪ�����ڣ����ͷ������á������������أ���Ȼ�Ͳ��������ã�commons-logging������ѡ����������־ʵ���ࡣ��

public class Test
{
    // commons-logging ���Ҳ����Լ���.properties�ļ�����ȥ��ϵͳ��������ȥ����������û��log4j��Jar��
    // ����ҵ�Log4j��Jar�ͻ��Զ�����Log4j��Jar
    private static Log log = LogFactory.getLog(Test.class);

    public void test()
    {
        log.debug(" 111 ");
        log.info(" 222 ");
        log.warn(" 333 ");
        log.error(" 444 ");
        log.fatal(" 555 ");
    }

    public static void main(String[] args)
    {
        Test testLog = new Test();
        testLog.test();
    }
}