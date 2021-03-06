package com.github.podchez.shtb.bot;

import com.github.podchez.shtb.command.CommandContainer;
import com.github.podchez.shtb.service.SendBotMessageServiceImpl;
import com.github.podchez.shtb.service.TelegramUserService;
import com.github.podchez.shtb.service.TelegramUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Locale;

import static com.github.podchez.shtb.command.CommandName.NO;

/**
 * Simple Habit Tracker - Telegram Bot
 */
@Component
public class SimpleHabitTrackerBot extends TelegramLongPollingBot {
    private static final String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    @Autowired
    public SimpleHabitTrackerBot(TelegramUserService telegramUserService) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.receiveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.receiveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
