package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(statements = "delete from Users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDbStorageTest {
    private final UserDbStorage userStorage;

    @Test
    void shouldReturnEmptyList() {
        assertThat(userStorage.getListUsers()).isEmpty();
    }

    @Test
    void shouldReturnListOfTwoUsers() {
        userStorage.addUser(getUser(1, "test1"));
        userStorage.addUser(getUser(2, "test2"));

        assertThat(userStorage.getListUsers()).hasSize(2);
    }

    @Test
    void shouldReturnNull_IfUserIsNotExists() {
        assertThat(userStorage.getUser(1)).isNull();
    }

    @Test
    void shouldReturnUser() {
        User user = userStorage.addUser(getUser(1, "test"));

        assertThat(userStorage.getUser(user.getId())).isEqualTo(getUser(user.getId(), "test"));
    }

    @Test
    void shouldReturnNullEmail_IfUserIsNotExists() {
        assertThat(userStorage.getByEmail("test")).isNull();
    }

    @Test
    void shouldReturnUserEmail() {
        User user = userStorage.addUser(getUser(1, "test"));

        assertThat(userStorage.getByEmail(user.getEmail())).isEqualTo(getUser(user.getId(), "test"));
    }

    @Test
    void update_shouldReturnNull_IfUserIsNotExists() {
        assertThat(userStorage.updateUser(1, getUser(1, "test"))).isNull();
    }

    @Test
    void update_shouldUpdateUser() {
        User user = userStorage.addUser(getUser(0, "test"));

        assertThat(userStorage.updateUser(user.getId(), user.withName("new name")))
                .hasFieldOrPropertyWithValue("name", "new name");
    }

    @Test
    void delete_shouldReturnNull_IfUserIsNotExists() {
        assertThat(userStorage.updateUser(1, getUser(1, "test"))).isNull();
    }

    @Test
    void delete_shouldDeleteUser() {
        User user = userStorage.addUser(getUser(0, "test"));

        assertThat(userStorage.getListUsers()).hasSize(1);
        assertThat(userStorage.delUser(user.getId())).isEqualTo(user);
        assertThat(userStorage.getListUsers()).isEmpty();
    }

    private User getUser(int id, String email) {
        return new User(
                id,
                email,
                "Test login",
                "Test name",
                LocalDate.EPOCH
        );
    }
}