package model;

import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import utilities.Numbers;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Bot extends BotTelegram {

    public void sendMsg(String text, String chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        //sendMessage.setReplyToMessageId(message.getMessageId());   just for replying to messages
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage.setText(text));  // (message.getText() + " " + text)
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);   // connect message with keyboard
        replyKeyboardMarkup.setSelective(true); // keyboard for all users (not particular)
        replyKeyboardMarkup.setResizeKeyboard(true); // sizing of keyboard
        replyKeyboardMarkup.setOneTimeKeyboard(true); // hide or not keyboard after pushing the button

        // create buttons
        List<KeyboardRow> keyboardRowLlist = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();  // first line of keyboard
        KeyboardRow keyboardSecondRow = new KeyboardRow();  // second

        //name buttons
        keyboardFirstRow.add(new KeyboardButton("/examen"));
        // keyboardFirstRow.add(new KeyboardButton("/sobremi"));
        keyboardSecondRow.add(new KeyboardButton("/tarea1"));
        keyboardSecondRow.add(new KeyboardButton("/tarea2"));
        keyboardSecondRow.add(new KeyboardButton("/tarea3"));
        keyboardSecondRow.add(new KeyboardButton("/tarea4"));
        keyboardSecondRow.add(new KeyboardButton("/tarea5"));
        // keyboardFirstRow.add(new KeyboardButton("/start"));

        keyboardRowLlist.add(keyboardFirstRow);  //add lines into array
        keyboardRowLlist.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowLlist);  //install list on keyboard
    }

    public void sendPoll(String chatId, File tarea) throws InterruptedException {
        int rangeOfnumbers = Numbers.getRandomNumberFrom(tarea.getArrayLength());
        //System.out.println("random number " + rangeOfnumbers);
        String[] answers = tarea.answers(rangeOfnumbers);
        SendPoll sendPoll = new SendPoll();

        // sendPoll.setChatId(message.getChatId().toString());
        sendPoll.setChatId(chatId);
        sendPoll.setAnonymous(true);
        sendPoll.setQuestion(tarea.question(rangeOfnumbers));
        sendPoll.setOptions(Arrays.asList(answers));
        sendPoll.setCorrectOptionId(tarea.correctOption(rangeOfnumbers));
        sendPoll.setType("quiz");

        try {
            execute(sendPoll.setChatId(chatId));  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendDoc(String chatId, String docLink) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(docLink);

        try {
            execute(sendDocument);  // (message.getText() + " " + text) как вариант
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendPollWithTimer(final String chatId, final File tarea, final int quantity) {
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < quantity; i++) {
                        TimeUnit.SECONDS.sleep(5);
                        sendPoll(chatId, tarea);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    sendMsg("error in timer", "290631155");
                }
            }
        };
        timer.schedule(t, 0l, 1000 * 60 * 60 * 24);
    }
}
