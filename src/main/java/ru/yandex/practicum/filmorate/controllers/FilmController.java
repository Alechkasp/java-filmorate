package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.services.InMemoryHistoryFilmService;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    public InMemoryHistoryFilmService filmService = new InMemoryHistoryFilmService();

    @GetMapping
    public List<Film> findAll() {
        log.debug("Получен запрос GET /films.");

        return filmService.getListFilms();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.debug("Получен запрос POST /films.");

        filmService.addFilm(film);
        log.debug("Фильм успешно создан!");

        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.debug("Получен запрос PUT /films.");

        filmService.updateFilm(film);
        log.debug("Фильм успешно обновлен!");

        return film;
    }
}