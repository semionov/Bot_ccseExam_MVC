package model;

import controller.MainController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotTelegram extends TelegramLongPollingBot {

    /*
    private String message_text;
    private long chat_id;

    // Getters
    public String getMessage_text() {
        return message_text;
    }
    public long getChat_id() {
        return chat_id;
    }
     */

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
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "ccse_test_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "1817038111:AAGEHr-WbukE1gP7pQrMU-WtcW0LFxA1gxk";
    }


}
