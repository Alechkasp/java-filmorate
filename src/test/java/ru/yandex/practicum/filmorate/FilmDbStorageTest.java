package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(statements = "delete from FILMS")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmDbStorageTest {
    private final FilmDbStorage filmStorage;

    @Test
    void shouldReturnEmptyList() {
        assertThat(filmStorage.getListFilms()).isEmpty();
    }

    @Test
    void shouldReturnListOfTwoFilms() {
        filmStorage.addFilm(getFilm(1));
        filmStorage.addFilm(getFilm(2));

        assertThat(filmStorage.getListFilms()).hasSize(2);
    }

    @Test
    void shouldReturnNull_IfFilmIsNotExists() {
        assertThat(filmStorage.getFilm(1)).isNull();
    }

    @Test
    void shouldReturnFilm() {
        Film film = filmStorage.addFilm(getFilm(1));

        assertThat(filmStorage.getFilm(film.getId())).isEqualTo(getFilm(film.getId()));
    }

    @Test
    void update_shouldReturnNull_IfFilmIsNotExists() {
        assertThat(filmStorage.updateFilm(1, getFilm(1))).isNull();
    }

    @Test
    void update_shouldUpdateFilm() {
        Film film = filmStorage.addFilm(getFilm(1));

        assertThat(filmStorage.updateFilm(film.getId(), film.withName("new name")))
                .hasFieldOrPropertyWithValue("name", "new name");
    }

    @Test
    void delete_shouldReturnNull_IfFilmIsNotExists() {
        assertThat(filmStorage.updateFilm(1, getFilm(1))).isNull();
    }

    @Test
    void delete_shouldDeleteFilm() {
        Film film = filmStorage.addFilm(getFilm(1));

        assertThat(filmStorage.getListFilms()).hasSize(1);
        assertThat(filmStorage.deleteFilm(film.getId())).isEqualTo(film);
        assertThat(filmStorage.getListFilms()).isEmpty();
    }

    private Film getFilm(int id) {
        return new Film(
                id,
                "Test film",
                "Test description",
                LocalDate.EPOCH,
                120,
                new Mpa(1, "G"),
                Collections.emptyList()
        );
    }
}
