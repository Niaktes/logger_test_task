package ru.niaktes.logger.messagegatherers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import ru.niaktes.logger.Level;

import static org.assertj.core.api.Assertions.assertThat;

class StandardMessageGathererTest {

    private final StandardMessageGatherer messageGatherer = new StandardMessageGatherer();

    @Test
    void whenGatherMessageDebugThenGetMessageWithTimeAndDebugLevel() {
        String expected = getDateAndTimeAsString()
                + " DEBUG "
                + "[message]";
        String actual = messageGatherer.gatherMessage(Level.DEBUG, "message");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenGatherMessageWarningThenGetMessageWithTimeAndWarningLevel() {
        String expected = getDateAndTimeAsString()
                + " WARN "
                + "[message]";
        String actual = messageGatherer.gatherMessage(Level.WARN, "message");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenChangeDateTimeFormatterThenGetChangedMessage() {
        DateTimeFormatter originalFormatter = messageGatherer.getDateTimeFormatter();
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");
        String expected = newFormatter.format(LocalDateTime.now())
                + " INFO "
                + "[other formatted message]";

        messageGatherer.setDateTimeFormatter(newFormatter);
        String actual = messageGatherer.gatherMessage(Level.INFO, "other formatted message");

        assertThat(actual).isEqualTo(expected);

        messageGatherer.setDateTimeFormatter(originalFormatter);
    }

    private String getDateAndTimeAsString() {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(LocalDateTime.now());
    }

}