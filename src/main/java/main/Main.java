package main;

import model.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            start();
    }

    public static void start() throws IOException {

        //Initialize bot and fill up the quiz
        BotContainer.load();
        FilesContainer.load();

        //Launch timer that makes bot send certain quantity of quiz every 24 hours
        BotContainer.getBot().sendPollWithTimer("290631155", FilesContainer.getFiles().get(0), 6);
    }
}