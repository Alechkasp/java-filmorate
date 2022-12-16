package ru.yandex.practicum.filmorate.storage.genre;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

public interface GenreStorage {
    List<Genre> getAll();
    List<Genre> getAllByFilmId(Integer filmId);
    Genre getGenreId(Integer id);
}