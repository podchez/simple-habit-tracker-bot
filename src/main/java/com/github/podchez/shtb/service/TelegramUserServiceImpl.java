package com.github.podchez.shtb.service;

import com.github.podchez.shtb.repository.TelegramUserRepository;
import com.github.podchez.shtb.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TelegramUserService} interface.
 */
@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository userRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        userRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return userRepository.findById(chatId);
    }
}
