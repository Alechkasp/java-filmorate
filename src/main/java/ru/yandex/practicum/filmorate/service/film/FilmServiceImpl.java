package ru.yandex.practicum.filmorate.service.film;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.GenreNotFoundException;
import ru.yandex.practicum.filmorate.exception.MpaNotFoundException;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final MpaStorage mpaStorage;
    private final GenreStorage genreStorage;

    public FilmServiceImpl(
            @Qualifier("dbFilmStorage") FilmStorage filmStorage,
            @Qualifier("dbUserStorage") UserStorage userStorage,
            MpaStorage mpaStorage,
            GenreStorage genreStorage
    ) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.mpaStorage = mpaStorage;
        this.genreStorage = genreStorage;
    }

    @Override
    public Film addFilm(Film newFilm) {
        Mpa mpa = mpaStorage.getMpaId(newFilm.getMpa().getId());

        if (mpa == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        if (newFilm.getGenres() != null) {
            newFilm.getGenres().forEach(genre -> {
                if (genreStorage.getGenreId(genre.getId()) == null) {
                    throw new GenreNotFoundException("Такого жанра нет");
                }
            });
        }

        return filmStorage.addFilm(newFilm);
    }

    @Override
    public Film updateFilm(Film film) {
        if (filmStorage.getFilm(film.getId()) == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        if (mpaStorage.getMpaId(film.getMpa().getId()) == null) {
            throw new MpaNotFoundException("Такого MPA нет");
        }

        if (film.getGenres() != null) {
            film.getGenres().forEach(genre -> {
                if (genreStorage.getGenreId(genre.getId()) == null) {
                    throw new GenreNotFoundException("Такого жанра нет");
                }
            });
        }

        return filmStorage.updateFilm(film.getId(), film);
    }

    @Override
    public Film deleteFilm(Integer id) {
        Film film = filmStorage.deleteFilm(id);

        if (film == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        return film;
    }

    @Override
    public Film getFilm(Integer id) {
        Film film = filmStorage.getFilm(id);

        if (film == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        return film;
    }

    @Override
    public List<Film> getListFilms() {
        return filmStorage.getListFilms();
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        Film film = filmStorage.getFilm(filmId);
        User user = userStorage.getUser(userId);

        if (film == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        filmStorage.addLike(filmId, userId);
    }

    @Override
    public void delLike(Integer filmId, Integer userId) {
        Film film = filmStorage.getFilm(filmId);
        User user = userStorage.getUser(userId);

        if (film == null) {
            throw new FilmNotFoundException("Такого фильма нет");
        }

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        filmStorage.delLike(filmId, userId);
    }

    @Override
    public List<Film> getPopularFilms(Integer count) {
        return filmStorage.getPopularFilms(count);
    }
}