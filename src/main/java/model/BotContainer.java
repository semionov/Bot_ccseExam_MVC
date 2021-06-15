package model;

import java.io.IOException;

public class BotContainer {

    private static Bot bot;

    public static Bot getBot() {
        return bot;
    }

    public static void load() throws IOException {
        bot = BotService.initializeBot();
    }
}
