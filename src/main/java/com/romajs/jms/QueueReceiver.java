package com.romajs.jms;

import com.romajs.jms.message.consumer.TextMessageConsumer;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

public class QueueReceiver {

    public static void main(String[] args) throws JMSException {
        Context context = new DefaultContext();
        QueueReceiver topicSubscriber = new QueueReceiver(context);
        topicSubscriber.receive(args[0]);
    }

    private final Context context;
    private Queue queue;

    public QueueReceiver(Context context) {
        this.context = context;
    }

    public void receive(String queueName) throws JMSException {

        try(Connection connection = context.getConnection()) {
            connection.start();

            try (Session session = context.getSession()) {
                queue = session.createQueue(queueName);
                MessageConsumer messageConsumer = session.createConsumer(queue);

                Message message = messageConsumer.receive();
                new TextMessageConsumer().accept(message);
            }
        }
    }
}
