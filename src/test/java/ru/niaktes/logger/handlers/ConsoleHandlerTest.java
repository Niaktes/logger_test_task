package ru.niaktes.logger.handlers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConsoleHandlerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream captor = new ByteArrayOutputStream();
    private final ConsoleHandler handler = new ConsoleHandler();

    @BeforeEach
    void setUp() {
        System.setErr(new PrintStream(captor));
    }

    @AfterEach
    void tearDown() {
        System.setErr(standardOut);
    }

    @Test
    void whenHandleMessageThenMessageWithNewLinePrintToError() {
        String message = "some message";
        String expected = "some message" + System.lineSeparator();
        handler.handleMessage(message);
        assertThat(captor).hasToString(expected);
    }

    @Test
    void whenHandleMessagesThenAllMessagesWithNewLinePrintToError() {
        String messageFirst = "some message";
        String messageSecond = "some other message";
        String expected = "some message"
                + System.lineSeparator()
                + "some other message"
                + System.lineSeparator();
        handler.handleMessage(messageFirst);
        handler.handleMessage(messageSecond);
        assertThat(captor).hasToString(expected);
    }

}