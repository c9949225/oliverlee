package net.oliver.olframework.util.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


//Log4j在哪里呢？它发挥作用了吗？
//应该注意到，我们上面给出的源代码，完全没有涉及到Log4j——这正是我们所希望的，这也正是commons-logging所要达到的目标之一。
//可是，怎么才能让Log4j发挥它的作用呢？答案很简单，只需满足“classpath中有Log4j的jar包”。
//前面已经说过了，commons-logging会自动发现并应用Log4j。
//所以只要它存在，它就发挥作用。（它不存在呢？自然就不发挥作用，commons-logging会另行选择其它的日志实现类。）

public class Test
{
    // commons-logging 在找不到自己的.properties文件后，再去找系统变量，再去找类里面有没有log4j的Jar包
    // 如果找到Log4j的Jar就会自动调用Log4j的Jar
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