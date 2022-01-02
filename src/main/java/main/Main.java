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
        //@CCSE_exam - channel ,  290631155 - mine
        BotContainer.getBot().sendPollWithTimer("@CCSE_exam", FilesContainer.getFiles().get(0), 3);
    }
}