package com.romajs.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

public interface Context {
    ConnectionFactory getConnectionFactory();
    Connection getConnection();
    Session getSession();
}
