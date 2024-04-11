package ru.niaktes.logger;

import lombok.Getter;

@Getter
public enum Level {

    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5),
    FATAL(6);

    private final int number;

    Level(int number) {
        this.number = number;
    }

}