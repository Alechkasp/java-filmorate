package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(statements = "delete from MPA")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaStorageTest {
    private final MpaStorage mpaStorage;

    @Test
    void shouldReturnEmptyList() {
        assertThat(mpaStorage.getAll()).isEmpty();
    }

    @Test
    void shouldReturnNull_IfMpaIsNotExists() {
        assertThat(mpaStorage.getMpaId(1)).isNull();
    }
}