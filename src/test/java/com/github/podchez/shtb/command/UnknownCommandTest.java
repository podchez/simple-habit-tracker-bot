package com.github.podchez.shtb.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.podchez.shtb.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class UnknownCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return "/asdasdasd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
