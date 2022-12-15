package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(statements = "delete from GENRE")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreStorageTest {
    private final GenreStorage genreStorage;

    @Test
    void shouldReturnEmptyList() {
        assertThat(genreStorage.getAll()).isEmpty();
    }

    @Test
    void shouldReturnNull_IfMpaIsNotExists() {
        assertThat(genreStorage.getGenreId(1)).isNull();
    }
}