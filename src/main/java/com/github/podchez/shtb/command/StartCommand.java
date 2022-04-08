package com.github.podchez.shtb.command;

import com.github.podchez.shtb.repository.entity.TelegramUser;
import com.github.podchez.shtb.service.SendBotMessageService;
import com.github.podchez.shtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String START_MESSAGE = "Hello! I'm a Simple Habit Tracker Bot. \uD83E\uDD16 \uD83D\uDCC5\n" +
            "I will help you to introduce good daily habits into your life as simply as possible.\n" +
            "To view the list of available commands, type /help";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser user = new TelegramUser();
                    user.setChatId(chatId);
                    user.setActive(true);
                    telegramUserService.save(user);
                });

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
