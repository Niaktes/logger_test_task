package ru.niaktes.logger.messagegatherers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import ru.niaktes.logger.Level;

@Getter
@Setter
public class StandardMessageGatherer implements MessageGatherer {

    private DateTimeFormatter dateTimeFormatter;

    public StandardMessageGatherer() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String gatherMessage(Level lvl, String text) {
        return String.format("%s %s [%s]",
                dateTimeFormatter.format(LocalDateTime.now()),
                lvl.name(),
                text);
    }

}