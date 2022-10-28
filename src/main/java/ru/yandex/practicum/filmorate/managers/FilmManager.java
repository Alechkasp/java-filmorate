package ru.yandex.practicum.filmorate.managers;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;

public interface FilmManager {
    Film addFilm(Film film);

    void updateFilm(Film film);

    List<Film> getListFilms();

    HashMap<Integer, Film> getTableFilms();
}
