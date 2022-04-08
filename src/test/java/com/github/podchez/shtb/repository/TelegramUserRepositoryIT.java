package com.github.podchez.shtb.repository;

import com.github.podchez.shtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

/**
 * Integration-level testing for {@link TelegramUserRepository}
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelegramUserRepositoryIT {
    @Autowired
    private TelegramUserRepository repository;

    @Sql(scripts = {"/sql/clear_db.sql", "/sql/add_tg_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers() {
        // when
        List<TelegramUser> users = repository.findAllByActiveTrue();

        // then
        Assertions.assertEquals(5, users.size());
    }

    @Sql(scripts = "/sql/clear_db.sql")
    @Test
    public void shouldProperlySaveTelegramUser() {
        // given
        TelegramUser user = new TelegramUser();
        user.setActive(true);
        user.setChatId("123456");
        repository.save(user);

        // when
        Optional<TelegramUser> savedUser = repository.findById(user.getChatId());

        // then
        Assertions.assertTrue(savedUser.isPresent());
        Assertions.assertEquals(user, savedUser.get());
    }
}
