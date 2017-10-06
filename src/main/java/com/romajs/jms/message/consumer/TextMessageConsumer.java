package com.romajs.jms.message.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.function.Consumer;

public class TextMessageConsumer implements Consumer<Message> {

    static final Logger logger = LoggerFactory.getLogger(TextMessageConsumer.class);

    @Override
    public void accept(Message message) {
        TextMessage textMessage = (TextMessage)  message;
        try {
            logger.info("receive messaged: {}", textMessage.getText());
            message.acknowledge();
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
