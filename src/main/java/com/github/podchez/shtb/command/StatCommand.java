package com.github.podchez.shtb.command;

import com.github.podchez.shtb.service.SendBotMessageService;
import com.github.podchez.shtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Statistics {@link Command}.
 */
public class StatCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STAT_MESSAGE = "%s people use Simple Habit Tracker Bot.";
    private static final String STAT_MESSAGE_ONE = "1 person uses Simple Habit Tracker Bot.";

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        int userCount = telegramUserService.retrieveAllActiveUsers().size();
        if (userCount != 1) {
            sendBotMessageService.sendMessage(chatId, String.format(STAT_MESSAGE, userCount));
        } else {
            sendBotMessageService.sendMessage(chatId, STAT_MESSAGE_ONE);
        }
    }
}
