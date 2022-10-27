package ru.yandex.practicum.filmorate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final HashMap<Integer, Film> films = new HashMap<>();
    public static final int MAX_LENGTH_DESCRIPTION = 200;
    public static final LocalDate DATE_FIRST_RELEASE = LocalDate.of(1895, 12, 28);
    public static final long DURATION_IS_NOT_NEGATIVE = 0;
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @GetMapping
    public List<Film> findAll() {
        log.debug("Получен запрос GET /films.");

        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        log.debug("Получен запрос POST /films.");

        if (film.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
            log.error("Максимальная длина описания — 200 символов");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        } else if (film.getReleaseDate().isBefore(DATE_FIRST_RELEASE)) {
            log.error("Дата релиза не может быть раньше 28 декабря 1895 года");
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        } else if (film.getDuration() < DURATION_IS_NOT_NEGATIVE) {
            log.error("Продолжительность фильма должна быть положительной");
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        } else {
            films.put(film.getId(), film);
            log.debug("Фильм успешно создан!");
        }
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        log.debug("Получен запрос PUT /films.");

        if (!films.containsKey(film.getId())) {
            log.error("Такого фильма нет");
            throw new ValidationException("Такого фильма нет");
        } else if (film.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
            log.error("Максимальная длина описания — 200 символов");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        } else if (film.getReleaseDate().isBefore(DATE_FIRST_RELEASE)) {
            log.error("Дата релиза не может быть раньше 28 декабря 1895 года");
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        } else if (film.getDuration() < DURATION_IS_NOT_NEGATIVE) {
            log.error("Продолжительность фильма должна быть положительной");
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        } else {
            films.get(film.getId()).setName(film.getName());
            films.get(film.getId()).setDescription(film.getDescription());
            films.get(film.getId()).setReleaseDate(film.getReleaseDate());
            films.get(film.getId()).setDuration(film.getDuration());
            log.debug("Фильм успешно обновлен!");
        }
        return film;
    }
}