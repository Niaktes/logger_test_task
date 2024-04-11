package ru.niaktes;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.niaktes.logger.Level;
import ru.niaktes.logger.Logger;
import ru.niaktes.logger.handlers.Handler;
import ru.niaktes.logger.messagegatherers.MessageGatherer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoggerTest {

    private Map<Handler, Level> originalHandlers;
    private MessageGatherer originalMessageGatherer;
    private final Logger logger = Logger.getInstance();

    @Mock
    private MessageGatherer messageGatherer;
    @Mock
    private Handler firstHandler;
    @Mock
    private Handler secondHandler;

    @BeforeEach
    void setUp() {
        originalHandlers = logger.getHandlers();
        originalMessageGatherer = logger.getMessageGatherer();
        logger.setHandlers(Map.of(
                firstHandler, Level.INFO,
                secondHandler, Level.ERROR)
        );
        logger.setMessageGatherer(messageGatherer);
    }

    @AfterEach
    void tearDown() {
        logger.setHandlers(originalHandlers);
        logger.setMessageGatherer(originalMessageGatherer);
    }

    @Test
    void loggerIsSingleton() {
        Logger first = Logger.getInstance();
        Logger second = Logger.getInstance();
        assertThat(first).isSameAs(second);
    }

    @Test
    void whenLogDebugLevelAndStringThenNoHandlersActivated() {
        String message = "message";
        logger.log(Level.DEBUG, message);
        verify(messageGatherer, times(1)).gatherMessage(Level.DEBUG, message);
        verify(firstHandler, times(0)).handleMessage(any());
        verify(secondHandler, times(0)).handleMessage(any());
    }

    @Test
    void whenLogWarnLevelAndStringThenOneHandlerActivated() {
        String message = "message";
        logger.log(Level.WARN, message);
        verify(messageGatherer, times(1)).gatherMessage(Level.WARN, message);
        verify(firstHandler, times(1)).handleMessage(any());
        verify(secondHandler, times(0)).handleMessage(any());
    }

    @Test
    void whenLogErrorLevelAndStringThenTwoHandlersActivated() {
        String message = "message";
        logger.log(Level.ERROR, message);
        verify(messageGatherer, times(1)).gatherMessage(Level.ERROR, message);
        verify(firstHandler, times(1)).handleMessage(any());
        verify(secondHandler, times(1)).handleMessage(any());
    }

    @Test
    void whenLogWarnLevelAndExceptionThenExceptionMessageHandled() {
        String message = "exception message";
        String expected = Level.WARN + " exceptionMessage";
        RuntimeException exception = new RuntimeException("exception message");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(messageGatherer.gatherMessage(any(Level.class), captor.capture()))
                .thenReturn(Level.WARN + " exceptionMessage");

        logger.log(Level.WARN, exception);

        verify(messageGatherer, times(1)).gatherMessage(Level.WARN, message);
        assertThat(captor.getValue()).isEqualTo(message);
        verify(firstHandler, times(1)).handleMessage(expected);
        verify(secondHandler, times(0)).handleMessage(any());
    }

    @Test
    void whenLogWarnLevelAndObjectThenObjectToStringHandled() {
        String message = "100";
        String expected = Level.WARN + " 100";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Integer object = 100;
        when(messageGatherer.gatherMessage(any(Level.class), captor.capture()))
                .thenReturn(Level.WARN + " 100");

        logger.log(Level.WARN, object);

        verify(messageGatherer, times(1)).gatherMessage(Level.WARN, message);
        assertThat(captor.getValue()).isEqualTo(message);
        verify(firstHandler, times(1)).handleMessage(expected);
        verify(secondHandler, times(0)).handleMessage(any());
    }

}