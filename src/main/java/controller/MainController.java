package controller;

import model.*;
import view.Messages;
import config.AppConfig;

import java.io.IOException;

public class MainController {

    public static void menu(String chatId, String text) {

        Bot bot = BotContainer.getBot();
        Stats stats = StatsContainer.getStatistics();


        switch (text) {
            case "/start":
                bot.sendMsg(Messages.greetings, chatId);
                break;
            case "/examen":
                sendPoll(bot, chatId, 0);
                stats.plusExam();
                break;
            case "/tarea1":
                sendPoll(bot, chatId, 1);
                stats.plusTask();
                break;
            case "/tarea2":
                sendPoll(bot, chatId, 2);
                stats.plusTask();
                break;
            case "/tarea3":
                sendPoll(bot, chatId, 3);
                stats.plusTask();
                break;
            case "/tarea4":
                sendPoll(bot, chatId, 4);
                stats.plusTask();
                break;
            case "/tarea5":
                sendPoll(bot, chatId, 5);
                stats.plusTask();
                break;
            case "/sobremi":
                bot.sendMsg(Messages.aboutMe, chatId);
                stats.plusInfo();
                break;
            case "/manual":
                bot.sendDoc(chatId, "https://examenes.cervantes.es/sites/default/files/Manual%20CCSE%202024_0.pdf");
                break;
            case "exit":
                bot.sendMsg("Closed", AppConfig.MY_CHAT_ID);
                System.exit(0);
                break;
            case "statistics":
                bot.sendMsg(stats.getStatsMessage(), chatId);
                break;
            default:
                break;
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
}
