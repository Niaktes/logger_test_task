package ru.niaktes.logger.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import lombok.AllArgsConstructor;
import ru.niaktes.logger.exceptions.FileHandlerException;

@AllArgsConstructor
public class FileHandler implements Handler {

    private Path filePath;

    @Override
    public void handleMessage(String msg) {
        try {
            Files.writeString(
                    filePath,
                    String.format("%s%s", msg, System.lineSeparator()),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new FileHandlerException("Problem with write to file process: " + e.getMessage());
        }
    }

}