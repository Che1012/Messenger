package com.db.edu;

import com.db.edu.creater.FileCreater;
import com.db.edu.message.Message;
import com.db.edu.message.SndMessage;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FileCreatorTest {

    private Message sndMessage;
    private FileCreater fileHandler;

    @BeforeEach
    public void TestsPreparation() {

        try {
            fileHandler = new FileCreater();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sndMessage = mock(SndMessage.class);

    }

    @Test
    public void shouldReturnLineToWriteInFile() {

        assertEquals(sndMessage.getIdentifier() + " " + sndMessage.getDateValue() + " "
                + sndMessage.getBody() + System.lineSeparator(), fileHandler.getLineToWrite(sndMessage));
    }

    @Test
    public void shouldWriteMessageToFile() throws IOException {

        Message newSndMessage = new SndMessage("123", new Date(System.currentTimeMillis()).toString(), "HW!");
        fileHandler.write(newSndMessage);

        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(fileHandler.getFile().getName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String last = "";
        String line ;
        while ((line = input.readLine()) != null) {
            last = line;
        }

        String expected = newSndMessage.getIdentifier() + " " + newSndMessage.getDateValue() + " "
                + newSndMessage.getBody();

        assertEquals(last, expected);

    }
}


