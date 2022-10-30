package ru.yandex.practicum.filmorate.services;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {
    Film addFilm(Film film);

    void updateFilm(Film film);

    List<Film> getListFilms();
}
