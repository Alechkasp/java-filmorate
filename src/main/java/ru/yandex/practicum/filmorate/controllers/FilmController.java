package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.FieldValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() {
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
        //validateFilmReleaseDate(film.getReleaseDate());
        //return filmService.addFilm(film);
        return addedFilm;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.debug("Получен запрос PUT /films.");

/*        validateFilmReleaseDate(film.getReleaseDate());
        return filmService.updateFilm(film);*/
        Film updatedFilm = filmService.updateFilm(film);
        log.debug("Фильм успешно обновлен!");

        return updatedFilm;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("Получен запрос PUT /films/{id}/like/{userId}");

        filmService.addLike(id, userId);

        log.debug("Лайк поставлен!");
    }

    @DeleteMapping("/{id}")
    public Film deleteFilm(@PathVariable Integer id) {
        log.debug("Получен запрос DELETE /films/{id}");

        return filmService.deleteFilm(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/like/{userId}")
    public void delLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("Получен запрос DELETE /films/{id}/like/{userId}");

        filmService.delLike(id, userId);
    }

/*    private void validateFilmReleaseDate(LocalDate date) {
        if (date.isBefore(LocalDate.of(1895, 12, 28))) {
            throw new FieldValidationException("releaseDate", "Release date must not be before 1895-12-28");
        }
    }*/
}