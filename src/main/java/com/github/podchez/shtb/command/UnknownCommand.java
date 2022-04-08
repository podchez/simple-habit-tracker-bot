package com.github.podchez.shtb.command;

import com.github.podchez.shtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String UNKNOWN_MESSAGE = "Command not found.\n" +
            "To view the list of available commands, type /help";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
