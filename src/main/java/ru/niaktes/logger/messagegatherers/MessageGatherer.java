package ru.niaktes.logger.messagegatherers;

import ru.niaktes.logger.Level;

public interface MessageGatherer {

    String gatherMessage(Level lvl, String text);

}