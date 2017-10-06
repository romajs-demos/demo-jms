package com.romajs.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

public class DefaultContext implements Context {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;

    public DefaultContext() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Session getSession() {
        return session;
    }
}
