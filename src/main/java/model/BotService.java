package model;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotService {

    public static Bot initializeBot() {

        // Initialize Api Context
        ApiContextInitializer.init();

        //declaration and instantiation of Bot object
        Bot bot = new Bot();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register bot
        try {
            botsApi.registerBot(bot);
        } catch (
            TelegramApiException e) {
            e.printStackTrace();
        }
        bot.sendMsg("initialized", "290631155");
        return bot;
    }
}
