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
        session = createSession(connection);
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

    @Override
    public Session createSession(Connection connection) throws JMSException {
        this.connection = connection;
        return this.connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
    }
}
