package com.project.jms;

import org.apache.activemq.ActiveMQConnection;

public class MessagerConfig {

    private static final String url = "tcp://localhost:61616";

    private static final String queueName = "MESSAGE_QUEUE";

    public static String getUrl(){
        return url;
    }

    public static String getQueueName(){
        return queueName;
    }


}
