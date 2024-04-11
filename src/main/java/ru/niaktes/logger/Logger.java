package ru.niaktes.logger;

import java.nio.file.Path;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import ru.niaktes.logger.handlers.ConsoleHandler;
import ru.niaktes.logger.handlers.FileHandler;
import ru.niaktes.logger.handlers.Handler;
import ru.niaktes.logger.messagegatherers.MessageGatherer;
import ru.niaktes.logger.messagegatherers.StandardMessageGatherer;

@Getter
@Setter
public class Logger {

    private static Logger instance;

    private Map<Handler, Level> handlers;
    private MessageGatherer messageGatherer;

    private Logger() {
        handlers = Map.of(
                new ConsoleHandler(), Level.WARN,
                new FileHandler(Path.of("log/log.txt")), Level.DEBUG
        );
        messageGatherer = new StandardMessageGatherer();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(Level lvl, String msg) {
        String logText = messageGatherer.gatherMessage(lvl, msg);
        for (Map.Entry<Handler, Level> entry : handlers.entrySet()) {
            Level handlerLevel = entry.getValue();
            Handler handler = entry.getKey();
            if (lvl.getNumber() >= handlerLevel.getNumber()) {
                handler.handleMessage(logText);
            }
        }
    }

    public void log(Level lvl, Exception e) {
        this.log(lvl, e.getMessage());
    }

    public void log(Level lvl, Object obj) {
        this.log(lvl, obj.toString());
    }

}

/*
2 часа писанины впустую.
1.5 часа на архитектуру.
6 часов на тесты и окончательную реализацию.
+ 2 часа на изучение существующего логгера.
 */
