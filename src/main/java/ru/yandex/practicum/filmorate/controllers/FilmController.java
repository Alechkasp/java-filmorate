package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> findAll() {
        log.debug("Получен запрос GET /films.");

        return filmService.getListFilms();
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable Integer id) {
        log.debug("Получен запрос GET /films/{id}");

        return filmService.getFilm(id);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam(defaultValue = "10") Integer count) {
        return filmService.getPopularFilms(count);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    @PutMapping("/{id}/like/{userId}")
    public Film addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("Получен запрос PUT /films/{id}/like/{userId}");

        Film film = filmService.addLike(id, userId);

        log.debug("Лайк поставлен!");

        return film;
    }

    @DeleteMapping
    public Film delete(@Valid @RequestBody Film film) {
        log.debug("Получен запрос DELETE /films.");

        Film removeFilm = filmService.deleteFilm(film);
        log.debug("Фильм успешно удален!");

        return removeFilm;
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Film delLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("Получен запрос DELETE /films/{id}/like/{userId}");

        Film film = filmService.delLike(id, userId);
        log.debug("Лайк удален!");

        return film;
    }
}