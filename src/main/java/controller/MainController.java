package controller;

import model.*;
import view.Messages;

import java.io.IOException;

public class MainController {

    public static void menu(String chatId, String text) {

        Bot bot = BotContainer.getBot();
        Stats stats = StatsContainer.getStatistics();

        if ("/start".equals(text)) {
            bot.sendMsg(Messages.greetings, chatId);
        } else if ("/examen".equals(text) || "Examen".equals(text)) {
            //sendMsg(message, "Whole exam");
            sendPoll(bot, chatId, 0);
            stats.plusExam();
        } else if ("/tarea1".equals(text) || "1".equals(text)) {
            sendPoll(bot, chatId, 1);
            stats.plusTask();
        } else if ("/tarea2".equals(text) || "2".equals(text)) {
            sendPoll(bot, chatId, 2);
            stats.plusTask();
        } else if ("/tarea3".equals(text)  || "3".equals(text)) {
            sendPoll(bot, chatId, 3);
            stats.plusTask();
        } else if ("/tarea4".equals(text)  || "4".equals(text)) {
            sendPoll(bot, chatId, 4);
            stats.plusTask();
        } else if ("/tarea5".equals(text)  || "5".equals(text)) {
            sendPoll(bot, chatId, 5);
            stats.plusTask();
        } else if ("/sobremi".equals(text)) {
            bot.sendMsg(Messages.aboutMe, chatId);
            stats.plusInfo();
        } else if ("/manual".equals(text)) {
            bot.sendDoc(chatId,
                "https://examenes.cervantes.es/sites/default/files/manual_ccse_2021.pdf");
        } else if ("exit".equals(text)) {
            bot.sendMsg("Closed", "290631155");
            System.exit(0);
        } else if ("statistics".equalsIgnoreCase(text)) {
            sendStats(bot, chatId, stats);
        }

    }

    public static void sendPoll(Bot bot, String chatId, int task) {
        if (task != 0) {
            bot.sendMsg(Messages.tareas[task], chatId);
        }

        try {
            bot.sendPoll(chatId, FileService.getTask(task));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendStats(Bot bot, String chatId, Stats stats) {
        bot.sendMsg(stats.getStatsMessage(), chatId);
    }
}
