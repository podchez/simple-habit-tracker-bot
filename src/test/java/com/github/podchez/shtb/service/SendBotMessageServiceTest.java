package com.github.podchez.shtb.service;

import com.github.podchez.shtb.bot.SimpleHabitTrackerBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
    private SendBotMessageService sendBotMessageService;
    private SimpleHabitTrackerBot simpleHabitTrackerBot;

    @BeforeEach
    public void init() {
        simpleHabitTrackerBot = Mockito.mock(SimpleHabitTrackerBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(simpleHabitTrackerBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        // given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);

        // when
        sendBotMessageService.sendMessage(chatId, message);

        // then
        Mockito.verify(simpleHabitTrackerBot).execute(sendMessage);
    }
}
