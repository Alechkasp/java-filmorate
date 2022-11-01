package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.services.InMemoryHistoryFilmService;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmTest {
    private InMemoryHistoryFilmService filmService;
    private Film film;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        filmService = new InMemoryHistoryFilmService();
        film = new Film();

        film.setId(1);
        film.setName("Spider-Man: No Way Home");
        film.setDescription("Yet another movie");
        film.setReleaseDate(LocalDate.of(2021, 12, 16));
        film.setDuration(148);
    }

    @Test
    void filmWithoutNameNull() {
        film.setName(null);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void filmWithoutNameEmptyString() {
        film.setName("");

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void filmWthDescriptionWithMore200Symbols() {
        film.setDescription("аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "ааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааа");

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    void releaseDateIsBefore28December1895() {
        film.setReleaseDate(LocalDate.of(1894, 12,28));

        assertThrows(ValidationException.class, () -> filmService.addFilm(film));
    }

    @Test
    void durationFilmNegative() {
        film.setDuration(-1);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}