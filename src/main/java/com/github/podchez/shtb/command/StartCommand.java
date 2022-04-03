package com.github.podchez.shtb.command;

import com.github.podchez.shtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String START_MESSAGE = "Hello! I'm a Simple Habit Tracker Bot. \uD83E\uDD16 \uD83D\uDCC5\n" +
            "I will help you to introduce good daily habits into your life as simply as possible.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
