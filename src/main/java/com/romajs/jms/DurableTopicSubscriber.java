package com.romajs.jms;

import com.romajs.jms.message.consumer.TextMessageConsumer;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public class DurableTopicSubscriber {

    public static void main(String[] args) throws JMSException {
        Context context = new DefaultContext();
        DurableTopicSubscriber topicSubscriber = new DurableTopicSubscriber(context);
        topicSubscriber.subscribe(args[0], args[1], args[2]);
    }

    private final Context context;
    private Topic topic;

    public DurableTopicSubscriber(Context context) {
        this.context = context;
    }

    public void subscribe(String topicName, String subscriptionName, String clientId) throws JMSException {

        Connection connection = context.getConnectionFactory().createConnection();
        connection.setClientID(clientId);
        connection.start();

        Session session = context.createSession(connection);
        topic = session.createTopic(topicName);

        MessageConsumer messageConsumer = session.createDurableSubscriber(topic, subscriptionName);
        messageConsumer.setMessageListener(new TextMessageConsumer()::accept);
    }

}
