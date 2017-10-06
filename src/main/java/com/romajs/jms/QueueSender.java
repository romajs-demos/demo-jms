package com.romajs.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

public class QueueSender {

    public static void main(String[] args) throws JMSException {
        Context context = new DefaultContext();
        QueueSender topicSubscriber = new QueueSender(context);
        topicSubscriber.send(args[0], args[1]);
    }

    private final Context context;
    private Queue queue;

    public QueueSender(Context context) {
        this.context = context;
    }

    public void send(String queueName, String text) throws JMSException {

        try(Connection connection = context.getConnection()) {
            connection.start();

            try(Session session = context.getSession()) {

                queue = session.createQueue(queueName);
                MessageProducer messageProducer = session.createProducer(queue);

                Message message = session.createTextMessage(text);
                messageProducer.send(message);
            }
        }
    }
}
