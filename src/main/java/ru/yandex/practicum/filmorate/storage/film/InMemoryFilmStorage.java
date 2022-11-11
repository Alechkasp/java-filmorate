package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private static final LocalDate DATE_FIRST_RELEASE = LocalDate.of(1895, 12, 28);
    private int id = 0;

    @Override
    public Film addFilm(Film film) {
        if (!film.getReleaseDate().isBefore(DATE_FIRST_RELEASE)) {
            id++;
            film.setId(id);
            films.put(film.getId(), film);
        } else {
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            film.setId(film.getId());
            films.put(film.getId(), film);
        } else {
            throw new FilmNotFoundException("Такого фильма нет");
        }
        return film;
    }

    @Override
    public Film deleteFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new FilmNotFoundException("Такого фильма нет");
        } else {
            Film removeFilm = films.get(film.getId());
            films.remove(film.getId());
            return removeFilm;
        }
    }

    @Override
    public Film getFilm(Integer id) {
        if (films.containsKey(id)) {
            return films.get(id);
        } else {
            throw new FilmNotFoundException("Такого фильма нет");
        }
    }

    @Override
    public List<Film> getListFilms() {
        return new ArrayList<>(films.values());
    }
}