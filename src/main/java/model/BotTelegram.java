package model;

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
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "CCSE_exam_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "1213851843:AAEYmoddKktWaYEd4ty4fiBjsyvoMJH8mW0";
    }

    /*
    public String getBotUsername() {
        return "CCSE_exam_bot";
    }

    public String getBotToken() {
        return "1213851843:AAEYmoddKktWaYEd4ty4fiBjsyvoMJH8mW0";
    }

 */
}
