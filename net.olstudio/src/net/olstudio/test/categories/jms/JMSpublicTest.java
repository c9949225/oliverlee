package net.olstudio.test.categories.jms;

import javax.jms.MessageListener;
import javax.naming.Context;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicPublisher;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import java.util.Properties;
import java.io.Serializable;
import javax.jms.Message;

/**
 <p>Title:JMS demo  </p>

 <p>Description: </p>

 <p>Copyright: Copyright (c) 2005</p>

 <p>Company: </p>

 @author lizongbo
 @version 1.0


 * Use this class to publish and subscribe to messages.
 * To send a text message:
 * <code>
 * JMSpublicTest jMSpublicTest = new JMSpublicTest();
 * jMSpublicTest.publishText("Hello world");
 * jMSpublicTest.close(); //Release resources
 * </code>

 * To receive a message:
 * <code>
 * JMSpublicTest jMSpublicTest = new JMSpublicTest();
 * jMSpublicTest.getTopicSubscriber();
 * </code>
 */
public class JMSpublicTest
    implements MessageListener {
  private static Context context;
  private boolean transacted = false;
  private int acknowledgementMode = javax.jms.Session.AUTO_ACKNOWLEDGE;
  private TopicConnectionFactory topicConnectionFactory;
  private TopicConnection topicConnection;
  private TopicSession topicSession;
  private TopicPublisher topicPublisher;
  private TopicSubscriber topicSubscriber;
  private Topic topic;
  private String topicConnectionFactoryName = "testjms";
  private String publishTopicName = "com.lizongbo.jms002";
  private String subscribeTopicName = "com.lizongbo.jms002";
  private String clientId = "";
  private String durableName = "";
  private boolean durable = false;
  public void setTransacted(boolean transacted) {
    this.transacted = transacted;
  }

  public TopicSubscriber getTopicSubscriber() throws Exception {
    if (topicSubscriber == null) {
      if (isDurable()) {
        topicSubscriber = getTopicSession(true).createDurableSubscriber(
            getSubscribeTopic(), getDurableName());
      }
      else {
        topicSubscriber = getTopicSession(true).createSubscriber(
            getSubscribeTopic());
      }
      topicSubscriber.setMessageListener(this);
      getTopicConnection(true).start();
    }
    return topicSubscriber;
  }

  public TopicPublisher getTopicPublisher() throws Exception {
    if (topicPublisher == null) {
      topicPublisher = getTopicSession(false).createPublisher(getPublishTopic());
    }
    return topicPublisher;
  }

  public Topic getPublishTopic() throws Exception {
    if (topic == null) {
      Object obj = getContext().lookup(publishTopicName);
      topic = (Topic) obj;
    }
    return topic;
  }

  public TopicSession getTopicSession(boolean consumer) throws Exception {
    if (topicSession == null) {
      topicSession = getTopicConnection(consumer).createTopicSession(
          isTransacted(), getAcknowledgementMode());
    }
    return topicSession;
  }

  public TopicConnection getTopicConnection(boolean consumer) throws Exception {
    if (topicConnection == null) {
      topicConnection = getTopicConnectionFactory().createTopicConnection();


      if (isDurable() && consumer) {
        topicConnection.setClientID(clientId);
      }
      topicConnection.start();
    }
    return topicConnection;
  }

  public TopicConnectionFactory getTopicConnectionFactory() throws Exception {
    if (topicConnectionFactory == null) {
      Object obj = getContext().lookup(topicConnectionFactoryName);
      topicConnectionFactory = (TopicConnectionFactory) obj;
    }
    return topicConnectionFactory;
  }

  public void setDurable(boolean durable) {
    this.durable = durable;
  }

  public boolean isDurable() {
    return durable;
  }

  public void setDurableName(String durableName) {
    this.durableName = durableName;
  }

  public String getDurableName() {
    return durableName;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setSubscribeTopicName(String subscribeTopicName) {
    this.subscribeTopicName = subscribeTopicName;
  }

  public String getSubscribeTopicName() {
    return subscribeTopicName;
  }

  public void setPublishTopicName(String publishTopicName) {
    this.publishTopicName = publishTopicName;
  }

  public String getPublishTopicName() {
    return publishTopicName;
  }

  public void setTopicConnectionFactoryName(String topicConnectionFactoryName) {
    this.topicConnectionFactoryName = topicConnectionFactoryName;
  }

  public String getTopicConnectionFactoryName() {
    return topicConnectionFactoryName;
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

  public static void main(String[] args) throws InterruptedException, Exception {

    JMSpublicTest jtc = new JMSpublicTest();
    jtc.setDurable(true);
    jtc.setClientId("ip192.168.9.226");
    jtc.setDurableName("test226aa");
    jtc.getTopicSubscriber();
    //开始发布一个消息
    JMSpublicTest jtp = new JMSpublicTest();
    jtp.setDurable(true);
    jtp.setClientId("ip192.168.9249");
    jtp.setDurableName("test226aa");
    jtp.publishText("发布一个消息！！！");
    jtp.close();
    //在这里保持监听来获取消息
    synchronized (jtc) {
      jtc.wait(10000);
    }
    jtc.close();
    System.out.println(jtp.getClientId());
    System.out.println("jieshu!!!");

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

  public Topic getSubscribeTopic() throws Exception {
    if (topic == null) {
      Object obj = getContext().lookup(subscribeTopicName);
      topic = (Topic) obj;
    }
    return topic;
  }

  public void publishText(String message) throws Exception {
    javax.jms.TextMessage textMessage = getTopicSession(false).
        createTextMessage();
    textMessage.clearBody();
    textMessage.setText(message);
    getTopicPublisher().publish(textMessage);
    if (isTransacted()) {
      getTopicSession(false).commit();
    }
  }

  public void publishObject(Serializable message) throws Exception {
    javax.jms.ObjectMessage objectMessage = getTopicSession(false).
        createObjectMessage();
    objectMessage.clearBody();
    objectMessage.setObject(message);
    getTopicPublisher().publish(objectMessage);
    if (isTransacted()) {
      getTopicSession(false).commit();
    }
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
        getTopicSession(false).commit();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public void close() throws Exception {
    if (topicPublisher != null) {
      topicPublisher.close();
    }
    if (topicSubscriber != null) {
      topicSubscriber.close();
    }
    if (topicSession != null) {
      topicSession.close();
    }
    if (topicConnection != null) {
      topicConnection.close();
    }
  }
}