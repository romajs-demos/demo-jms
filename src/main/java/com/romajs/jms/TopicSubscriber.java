package com.romajs.jms;

import com.romajs.jms.message.consumer.TextMessageConsumer;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Topic;

public class TopicSubscriber {

    public static void main(String[] args) throws JMSException {
        Context context = new DefaultContext();
        TopicSubscriber topicSubscriber = new TopicSubscriber(context);
        topicSubscriber.subscribe(args[0]);
    }

    private final Context context;
    private Topic topic;

    public TopicSubscriber(Context context) {
        this.context = context;
    }

    public void subscribe(String topicName) throws JMSException {

        context.getConnection().start();
        topic = context.getSession().createTopic(topicName);

        MessageConsumer messageConsumer = context.getSession().createConsumer(topic);
        messageConsumer.setMessageListener(new TextMessageConsumer()::accept);
    }

}
