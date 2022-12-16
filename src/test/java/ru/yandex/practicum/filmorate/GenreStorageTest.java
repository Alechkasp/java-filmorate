package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreStorageTest {
    private final GenreStorage genreStorage;

    @Test
    void shouldReturnSizeList() {

        assertThat(genreStorage.getAll()).hasSize(6);
    }

    @Test
    void shouldReturnFirstGenre() {
        Genre genre = new Genre(1, "Комедия");

        assertThat(genreStorage.getGenreId(1)).isEqualTo(genre);
    }
}