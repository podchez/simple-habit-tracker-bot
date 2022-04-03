package com.github.podchez.shtb.command;

import com.github.podchez.shtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.podchez.shtb.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>❕ Available commands:</b>\n\n" +
            "%s - start command\n" +
            "%s - stop command\n" +
            "%s - help command\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
