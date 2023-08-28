package com.project.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;

import javax.jms.*;

public class MessagerReceiver {

    public static void main(String[] args){
        System.out.println("URL++++++++++++++" + MessagerConfig.getUrl());
        String url = MessagerConfig.getUrl();
        String queueName = MessagerConfig.getQueueName();

//      Getting Jms Connection from the JMS server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        try (Connection connection = connectionFactory.createConnection();) {
            connection.start();

//          Creating a non-transactional session to send/receive JMS message.
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);

            Message message = (Message) consumer.receive();

            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Received message" + "'" + textMessage.getText() + "'");
            }

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
