package com.github.podchez.shtb.command;

import com.github.podchez.shtb.bot.SimpleHabitTrackerBot;
import com.github.podchez.shtb.service.SendBotMessageService;
import com.github.podchez.shtb.service.SendBotMessageServiceImpl;
import com.github.podchez.shtb.service.TelegramUserService;
import com.github.podchez.shtb.service.TelegramUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link Command}s.
 */
public abstract class AbstractCommandTest {
    protected SimpleHabitTrackerBot simpleHabitTrackerBot = Mockito.mock(SimpleHabitTrackerBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(simpleHabitTrackerBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        // given
        Long chatId = 1234567891234L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        // when
        getCommand().execute(update);

        // then
        Mockito.verify(simpleHabitTrackerBot).execute(sendMessage);
    }
}
