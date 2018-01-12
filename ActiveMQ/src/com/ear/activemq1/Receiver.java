package com.ear.activemq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * <p>Title:Receiver</p>
 * <p>Description: JMS������Ϣ</p>
 * <p>Company: ecar</p> 
 * @author hexiaoyun 
 * @date 2017-2-22 ����03:01:55
*/
public class Receiver {
	    public static void main(String[] args) {      
	        ConnectionFactory connectionFactory; // ConnectionFactory �����ӹ�����JMS ��������  
	        Connection connection = null;      // Connection ��JMS �ͻ��˵�JMS  
	        Session session; // Session�� һ�����ͻ������Ϣ���߳�    
	        Destination destination;// Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭. 
	        MessageConsumer  consumer ;   // MessageConsumer����Ϣ������ 
	        // ����ConnectionFactoryʵ����󣬴˴�����ActiveMq��ʵ��jar  
	        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
	        try { // ����ӹ����õ����Ӷ���  
	            connection = connectionFactory.createConnection();  
	            // ��������
	            connection.start();  
	            // ��ȡ��������  
	            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
	            // ��ȡsessionע�����ֵxingbo.xu-queue��һ����������queue��������ActiveMq��console����  
	            destination = session.createQueue("FirstQueue");  
	            // �õ���Ϣ����ߡ������ߡ�  
	            consumer = session.createConsumer(destination);  
	            while(true){
	            	//���ý����߽�����Ϣ��ʱ�䣬Ϊ�˱��ڲ��ԣ����ﶨΪ100s
	            	TextMessage message = (TextMessage) consumer.receive(90000000);
	            	 if (null != message) {
	            		  System.out.println("consumer�յ���Ϣ��" + message.getText());
	                 } else {
	                     break;
	                 }
	            }
         		  session.commit();     //ֻ��sessionִ��commit����ܳɹ������Ϣ
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (null != connection)  
	                    connection.close();  
	            } catch (Throwable ignore) {  
	            }  
	        }  
	    }  
	    
}
