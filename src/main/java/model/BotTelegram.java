package model;

import config.AppConfig;
import controller.MainController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotTelegram extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String chatId = "";
        String text = "";

        // We check if the update has a message and the message has text and invoke menu in positive case
        if (message != null && message.hasText()) {
            text = message.getText();
            chatId = message.getChatId().toString();

            MainController.menu(chatId, text);
        }
    }


    @Override
    public String getBotUsername() {
        // Return bot username
        return AppConfig.BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return AppConfig.TELEGRAM_BOT_TOKEN;
    }


}
