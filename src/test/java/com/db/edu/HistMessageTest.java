package com.db.edu;

import com.db.edu.creater.FileCreater;
import com.db.edu.message.HistMessage;
import com.db.edu.message.Message;
import com.db.edu.message.SndMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class HistMessageTest {



    private FileCreater fileHandler;

    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.lineSeparator();
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }

    @BeforeEach
    public void TestsPreparation() {

        try {
            fileHandler = new FileCreater();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void shouldAddMessageToHistory() throws IOException {

        Message newHistMessage = new HistMessage("123", new Date(System.currentTimeMillis()).toString(), "hist");
        fileHandler.write(newHistMessage);
        newHistMessage.makeReturnMessage(fileHandler);

        assertEquals(readUsingBufferedReader(fileHandler.getFile().getName()),newHistMessage.getReturnMessage());


    }
}
