package com.db.edu;

import com.db.edu.connection.Connection;
import com.db.edu.connection.Proxy;
import com.db.edu.message.Message;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ProxyTest {

    @Test
    public void shouldThrowExceptionWhenMessageIsNull() {

        Connection connector = mock(Connection.class)  ;
    Proxy newProxy = new Proxy(connector);

        assertThrows(NullPointerException.class,
                () -> newProxy.send(null));
    }


    @Disabled
    @Test
    public void shouldThrowExceptionWhenNumberOfAttemptsExceeded() throws IOException {

        Connection connector = mock(Connection.class)  ;
        Proxy newProxy = new Proxy(connector);
        Message message = mock(Message.class);
        ObjectOutputStream output = mock(ObjectOutputStream.class);
        doThrow(IOException.class).when(output).writeObject(message);


        assertThrows(IOException.class,
                () -> newProxy.send(message));
    }
}
