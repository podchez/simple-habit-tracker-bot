package com.github.podchez.shtb.command;

import com.github.podchez.shtb.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.podchez.shtb.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {
    private final ImmutableMap<Object, Object> commandMap;

    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command receiveCommand(String commandIdentifier) {
        return (Command) commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
