package com.github.podchez.shtb.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.podchez.shtb.command.CommandName.NO;
import static com.github.podchez.shtb.command.NoCommand.NO_MESSAGE;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
