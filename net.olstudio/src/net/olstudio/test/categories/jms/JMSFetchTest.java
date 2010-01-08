package net.olstudio.test.categories.jms;

/**
 *
 <p>Title:JMS demo  </p>

 <p>Description: </p>

 <p>Copyright: Copyright (c) 2005</p>

 <p>Company: </p>

 @author lizongbo
 @version 1.0
 */
public class JMSFetchTest {
  public JMSFetchTest() {
    super();
  }

  public static void main(String[] args) throws Exception {
    JMSTest jt = new JMSTest();
    jt.getQueueReceiver();
    synchronized (jt) {
      jt.wait(10000);
    }
    System.out.println("jieshu!!!");
    //jt.sendObject("test jms message!!!--lizongbo");
    //jt.sendObject("²âÊÔjmsÏûÏ¢£¡£¡£¡--lizongbo");
  }

}