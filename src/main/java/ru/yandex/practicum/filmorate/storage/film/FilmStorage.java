package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);
    Film updateFilm(Integer id, Film film);
    Film deleteFilm(Integer id);
    Film getFilm(Integer id);
    List<Film> getListFilms();
    void addLike(Integer filmId, Integer userId);
    void delLike(Integer filmId, Integer userId);
    List<Film> getPopularFilms(Integer count);
}