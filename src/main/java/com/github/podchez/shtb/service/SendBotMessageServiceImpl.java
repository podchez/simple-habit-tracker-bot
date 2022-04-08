package com.github.podchez.shtb.service;

import com.github.podchez.shtb.bot.SimpleHabitTrackerBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final SimpleHabitTrackerBot simpleHabitTrackerBot;

    @Autowired
    public SendBotMessageServiceImpl(SimpleHabitTrackerBot simpleHabitTrackerBot) {
        this.simpleHabitTrackerBot = simpleHabitTrackerBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.enableHtml(true);
        try {
            simpleHabitTrackerBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO add logging to the project.
            e.printStackTrace();
        }
    }
}
