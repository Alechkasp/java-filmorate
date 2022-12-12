package ru.yandex.practicum.filmorate.storage.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbGenreStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        String sql = "select * from GENRE order by GENRE_ID";
        try {
            return jdbcTemplate.query(sql, this::mapRowToGenre);
        } catch (DataAccessException exception) {
            return null;
        }
    }

    @Override
    public Genre getGenreId(Integer id) {
        String sql = "select * from GENRE where GENRE_ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToGenre, id);
        } catch (DataAccessException exception) {
            return null;
        }
    }

    @Override
    public List<Genre> getAllByFilmId(Integer filmId) {
        String sql = "select FG.GENRE_ID as GENRE_ID, G.GENRE_NAME as GENRE_NAME " +
                "from FILM_GENRE as FG " +
                "join GENRE as G on FG.GENRE_ID = G.GENRE_ID " +
                "where FG.FILM_ID = ? ";
        try {
            return jdbcTemplate.query(sql, this::mapRowToGenre, filmId);
        } catch (DataAccessException exception) {
            return null;
        }
    }

    private Genre mapRowToGenre(ResultSet resultSet, int rowNum) throws SQLException {
        return new Genre(resultSet.getInt("GENRE_ID"), resultSet.getString("GENRE_NAME"));
    }
}