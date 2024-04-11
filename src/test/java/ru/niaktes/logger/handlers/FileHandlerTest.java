package ru.niaktes.logger.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileHandlerTest {

    private Path tempFile;
    private FileHandler handler;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("testLogFile", null);
        handler = new FileHandler(tempFile);
    }

    @Test
    void whenHandleMessageThenMessagePrintToFile() throws IOException {
        String message = "some message";
        String expected = "some message";

        handler.handleMessage(message);

        List<String> content = Files.readAllLines(tempFile);
        String actual = String.join("", content);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenHandleMessagesThenMessagesPrintToFileOnNewLines() throws IOException {
        String messageFirst = "some message";
        String messageSecond = "some other message";
        String expectedFirstLine = "some message";
        String expectedSecondLine = "some other message";

        handler.handleMessage(messageFirst);
        handler.handleMessage(messageSecond);

        List<String> content = Files.readAllLines(tempFile);
        assertThat(content).hasSizeGreaterThan(1);
        assertThat(content.get(0)).isEqualTo(expectedFirstLine);
        assertThat(content.get(1)).isEqualTo(expectedSecondLine);
    }

}