package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.services.FilmService;
import ru.yandex.practicum.filmorate.services.InMemoryHistoryFilmService;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService = new InMemoryHistoryFilmService();

    @GetMapping
    public List<Film> findAll() {
        log.debug("Получен запрос GET /films.");

        return filmService.getListFilms();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.debug("Получен запрос POST /films.");

        Film addedFilm = filmService.addFilm(film);
        log.debug("Фильм успешно создан!");

        return addedFilm;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.debug("Получен запрос PUT /films.");

        Film updatedFilm = filmService.updateFilm(film);
        log.debug("Фильм успешно обновлен!");

        return updatedFilm;
    }
}