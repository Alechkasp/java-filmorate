package ru.yandex.practicum.filmorate.storage.film;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("FilmDbStorage")
@RequiredArgsConstructor
public class FilmDbStorage implements FilmStorage {
    private final JdbcTemplate jdbcTemplate;
    private final GenreStorage genreStorage;

    @Override
    public Film addFilm(Film film) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("films")
                .usingGeneratedKeyColumns("film_id");

        Map<String, Object> filmColumns = new HashMap<>();
        filmColumns.put("film_name", film.getName());
        filmColumns.put("description", film.getDescription());
        filmColumns.put("release_date", film.getReleaseDate());
        filmColumns.put("duration", film.getDuration());
        filmColumns.put("mpa_id", film.getMpa().getId());

        int filmId = simpleJdbcInsert.executeAndReturnKey(filmColumns).intValue();

        updateFilmGenres(film.getGenres(), filmId);

        return getFilm(filmId);
    }

    @Override
    public Film updateFilm(Integer id, Film film) {
        jdbcTemplate.update(
                FilmQueries.UPDATE,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpa().getId(),
                id
        );

        jdbcTemplate.update(FilmQueries.DELETE_FILM_GENRES, film.getId());
        updateFilmGenres(film.getGenres(), id);

        return getFilm(id);
    }

    @Override
    public Film deleteFilm(Integer id) {
        Film film = getFilm(id);

        if (film == null) {
            return null;
        }

        jdbcTemplate.update(FilmQueries.DELETE, id);

        return film;
    }

    @Override
    public Film getFilm(Integer id) {
        try {
            return jdbcTemplate.queryForObject(FilmQueries.GET_BY_ID, this::mapRowToFilm, id);
        } catch (DataAccessException exception) {
            return null;
        }
    }

    @Override
    public List<Film> getListFilms() {
        return jdbcTemplate.query(FilmQueries.GET_ALL, this::mapRowToFilm);
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        jdbcTemplate.update(FilmQueries.LIKE_FILM, filmId, userId);
    }

    @Override
    public void delLike(Integer filmId, Integer userId) {
        jdbcTemplate.update(FilmQueries.DELETE_LIKE_FROM_FILM, filmId, userId);
    }

    @Override
    public List<Film> getPopularFilms(Integer count) {
        return jdbcTemplate.query(FilmQueries.GET_POPULAR_FILMS, this::mapRowToFilm, Math.max(count, 0));
    }

    private Film mapRowToFilm(ResultSet resultSet, Integer rowNum) throws SQLException {
        return new Film(
                resultSet.getInt("film_id"),
                resultSet.getString("film_name"),
                resultSet.getString("description"),
                resultSet.getDate("release_date").toLocalDate(),
                resultSet.getInt("duration"),
                new Mpa(resultSet.getInt("mpa_id"), resultSet.getString("mpa_name")),
                genreStorage.getAllByFilmId(resultSet.getInt("film_id"))
        );
    }

    private void updateFilmGenres(List<Genre> genres, Integer filmId) {
        if (genres != null) {
            List<Integer> genreUniqueIds = genres.stream()
                    .map(Genre::getId)
                    .distinct()
                    .collect(Collectors.toList());

            jdbcTemplate.batchUpdate(
                    FilmQueries.ADD_GENRE,
                    new BatchPreparedStatementSetter() {
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            int genreId = genreUniqueIds.get(i);
                            ps.setInt(1, filmId);
                            ps.setInt(2, genreId);
                        }

                        public int getBatchSize() {
                            return genreUniqueIds.size();
                        }
                    });
        }
    }
}