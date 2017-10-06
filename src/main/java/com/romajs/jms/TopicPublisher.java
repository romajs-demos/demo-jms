package com.romajs.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

public class TopicPublisher {

    public static void main(String[] args) throws JMSException {
        Context context = new DefaultContext();
        TopicPublisher topicSubscriber = new TopicPublisher(context);
        topicSubscriber.publish(args[0], args[1]);
    }

    private final Context context;
    private Topic topic;

    public TopicPublisher(Context context) {
        this.context = context;
    }

    public void publish(String topicName, String text) throws JMSException {

        try(Connection connection = context.getConnection()) {
            connection.start();

            try(Session session = context.getSession()) {

                topic = session.createTopic(topicName);
                MessageProducer messageProducer = session.createProducer(topic);

                Message message = session.createTextMessage(text);
                messageProducer.send(message);
            }
        }
    }

}
