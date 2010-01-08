package net.olstudio.test.categories.jms;

import javax.jms.MessageListener;
import javax.naming.Context;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.QueueReceiver;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.Properties;
import javax.jms.Message;
import javax.jms.JMSException;

/**
 <p>Title:JMS demo  </p>

 <p>Description: </p>

 <p>Copyright: Copyright (c) 2005</p>

 <p>Company: </p>

 @author lizongbo
 @version 1.0


 * Use this class to send and receive point-to-point messages.
 * To send a text message:
 * <code>
 * JMSTest jMSTest = new JMSTest();
 * jMSTest.sendText("Hello world");
 * jMSTest.close(); //Release resources
 * </code>

 * To receive a message:
 * <code>
 * JMSTest jMSTest = new JMSTest();
 * jMSTest.getQueueReceiver();
 * </code>
 */
public class JMSTest
    implements MessageListener {
  private static Context context;
  private boolean transacted = true;
  private int acknowledgementMode = javax.jms.Session.AUTO_ACKNOWLEDGE;
  private QueueConnectionFactory queueConnectionFactory;
  private QueueConnection queueConnection;
  private QueueSession queueSession;
  private QueueSender queueSender;
  private QueueReceiver queueReceiver;
  private Queue queue;
  private String queueConnectionFactoryName = "testjms";
  private String sendQueueName = "com.lizongbo.jms001";
  private String recvQueueName = "com.lizongbo.jms001";
  public void setTransacted(boolean transacted) {
    this.transacted = transacted;
  }

  public void sendObject(Serializable message) throws Exception {
    javax.jms.ObjectMessage objectMessage = getQueueSession().
        createObjectMessage();
    objectMessage.clearBody();
    objectMessage.setObject(message);
    getQueueSender().send(objectMessage);
    if (isTransacted()) {
      getQueueSession().commit();
    }
  }

  public void sendText(String message) throws Exception {
    javax.jms.TextMessage textMessage = getQueueSession().createTextMessage();
    textMessage.clearBody();
    textMessage.setText(message);
    getQueueSender().send(textMessage);
    if (isTransacted()) {
      getQueueSession().commit();
    }
  }

  public QueueReceiver getQueueReceiver() throws Exception {
    if (queueReceiver == null) {
      queueReceiver = getQueueSession().createReceiver(getRecvQueue());
      queueReceiver.setMessageListener(this);
    }
    return queueReceiver;
  }

  public QueueSender getQueueSender() throws Exception {
    if (queueSender == null) {
      queueSender = getQueueSession().createSender(getSendQueue());
    }
    return queueSender;
  }

  public Queue getRecvQueue() throws Exception {
    if (queue == null) {
      Object obj = getContext().lookup(recvQueueName);
      queue = (Queue) obj;
    }
    return queue;
  }

  public Queue getSendQueue() throws Exception {
    if (queue == null) {
      Object obj = getContext().lookup(sendQueueName);
      queue = (Queue) obj;
    }
    return queue;
  }

  public QueueSession getQueueSession() throws Exception {
    if (queueSession == null) {
      queueSession = getQueueConnection().createQueueSession(isTransacted(),
          getAcknowledgementMode());
    }
    return queueSession;
  }

  public QueueConnection getQueueConnection() throws Exception {
    if (queueConnection == null) {
      queueConnection = getQueueConnectionFactory().createQueueConnection();
      queueConnection.start();
    }
    return queueConnection;
  }

  public QueueConnectionFactory getQueueConnectionFactory() throws Exception {
    if (queueConnectionFactory == null) {
      Object obj = getContext().lookup(queueConnectionFactoryName);
      queueConnectionFactory = (QueueConnectionFactory) obj;
    }
    return queueConnectionFactory;
  }

  public void setRecvQueueName(String recvQueueName) {
    this.recvQueueName = recvQueueName;
  }

  public String getRecvQueueName() {
    return recvQueueName;
  }

  public void setSendQueueName(String sendQueueName) {
    this.sendQueueName = sendQueueName;
  }

  public String getSendQueueName() {
    return sendQueueName;
  }

  public void setQueueConnectionFactoryName(String queueConnectionFactoryName) {
    this.queueConnectionFactoryName = queueConnectionFactoryName;
  }

  public String getQueueConnectionFactoryName() {
    return queueConnectionFactoryName;
  }

  public void setAcknowledgementMode(int acknowledgementMode) {
    this.acknowledgementMode = acknowledgementMode;
  }

  public int getAcknowledgementMode() {
    return acknowledgementMode;
  }

  public boolean isTransacted() {
    return transacted;
  }

  public static void main(String[] args) throws Exception {
    JMSTest jt = new JMSTest();
    jt.sendText("test jms Text  message!!!--lizongbo " + new java.util.Date().toString());
    jt.sendText("测试jms文本消息！！！--lizongbo" + new java.util.Date().toString());
  }

  public Context getInitialContext() throws Exception {
//    String url = "t3://*.*:7001";
//    String user = null;
//    String password = null;
     String url = "t3://127.0.0.1:7001";
    String user = "weblogic";
    String password = "weblogic";

    Properties properties;
    try {
      properties = new Properties();
      properties.put(Context.INITIAL_CONTEXT_FACTORY,
                     "weblogic.jndi.WLInitialContextFactory");
      properties.put(Context.PROVIDER_URL, url);
      if (user != null) {
        properties.put(Context.SECURITY_PRINCIPAL, user);
        properties.put(Context.SECURITY_CREDENTIALS,
                       password == null ? "" : password);
      }
      return new javax.naming.InitialContext(properties);
    }
    catch (Exception e) {
      System.out.println("Unable to connect to WebLogic server at " + url);
      System.out.println("Please make sure that the server is running.");
      throw e;
    }
  }

  private Context getContext() throws Exception {
    if (context == null) {
      try {
        context = getInitialContext();
      }
      catch (Exception ex) {
        ex.printStackTrace();
        throw ex;
      }
    }
    return context;
  }

  public void onMessage(Message message) {
    if (message instanceof javax.jms.BytesMessage) {
      javax.jms.BytesMessage bytesMessage = (javax.jms.BytesMessage) message;
      System.out.println("这是一个BytesMessage，内容是：" + bytesMessage);
      /** @todo Process bytesMessage here */
    }
    else if (message instanceof javax.jms.MapMessage) {
      javax.jms.MapMessage mapMessage = (javax.jms.MapMessage) message;
      System.out.println("这是一个MapMessage，内容是：" + mapMessage);
      /** @todo Process mapMessage here */
    }
    else if (message instanceof javax.jms.ObjectMessage) {
      javax.jms.ObjectMessage objectMessage = (javax.jms.ObjectMessage) message;
      System.out.println("这是一个objectMessage，内容是：" + objectMessage);
      /** @todo Process objectMessage here */
    }
    else if (message instanceof javax.jms.StreamMessage) {
      javax.jms.StreamMessage streamMessage = (javax.jms.StreamMessage) message;
      System.out.println("这是一个StreamMessage，内容是：" + streamMessage);
      /** @todo Process streamMessage here */
    }
    else if (message instanceof javax.jms.TextMessage) {
      javax.jms.TextMessage objectMessage = (javax.jms.TextMessage) message;
      System.out.println("这是一个TextMessage，内容是：" + objectMessage);
      /** @todo Process textMessage here */
    }
    if (isTransacted()) {
      try {
        getQueueSession().commit();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public void close() throws Exception {
    if (queueSender != null) {
      queueSender.close();
    }
    if (queueReceiver != null) {
      queueReceiver.close();
    }
    if (queueSession != null) {
      queueSession.close();
    }
    if (queueConnection != null) {
      queueConnection.close();
    }
  }
}