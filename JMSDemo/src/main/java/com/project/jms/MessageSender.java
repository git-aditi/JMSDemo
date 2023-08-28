package com.project.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSender {
    public static void main(String[] args) {

        System.out.println("URL++++++++++++++" + MessagerConfig.getUrl());
        String url = MessagerConfig.getUrl();
        String queueName = MessagerConfig.getQueueName();

//      Getting Jms Connection from the JMS server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();

//          Creating a non-transactional session to send/receive JMS message.
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//            The Queue will be created automatically on the server.
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage("Vicky");

//          Hear we are sending our message.

            producer.send(message);

            System.out.println("Message" + " " + message.getText() + " " + "send sucessfully to the queue.");

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
