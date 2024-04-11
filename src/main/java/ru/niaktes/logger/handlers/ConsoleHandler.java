package ru.niaktes.logger.handlers;

public class ConsoleHandler implements Handler {

    @Override
    public void handleMessage(String msg) {
        System.err.println(msg);
    }

}