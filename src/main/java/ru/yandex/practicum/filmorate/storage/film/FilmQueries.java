package ru.yandex.practicum.filmorate.storage.film;

public class FilmQueries {
    static final String GET_ALL = "select * " +
            "from FILMS as F " +
            "join MPA as M on F.MPA_ID = M.MPA_ID";

    static final String GET_BY_ID = "select * " +
            "from FILMS as F " +
            "join MPA as M on F.MPA_ID = M.MPA_ID " +
            "where F.FILM_ID = ?";

    static final String UPDATE = "update FILMS " +
            "set " +
            "FILM_NAME = ?, " +
            "DESCRIPTION = ?, " +
            "RELEASE_DATE = ?, " +
            "DURATION = ?, " +
            "MPA_ID = ? " +
            "where FILM_ID = ?";

    static final String DELETE = "delete from FILMS where FILM_ID = ?";

    static final String GET_POPULAR_FILMS = "select " +
            "F.FILM_ID, " +
            "F.FILM_NAME, " +
            "F.DESCRIPTION, " +
            "F.RELEASE_DATE, " +
            "F.DURATION, " +
            "M.MPA_ID as MPA_ID, " +
            "M.MPA_NAME as MPA_NAME, " +
            "G.GENRE_ID, " +
            "G.GENRE_NAME as GENRE_NAME, " +
            "count(L.FILM_ID) as LIKES " +
            "from FILMS as F " +
            "inner join MPA as M on F.MPA_ID = M.MPA_ID " +
            "left join FILM_GENRE as FG on F.FILM_ID = FG.FILM_ID " +
            "left join GENRE as G on FG.GENRE_ID = G.GENRE_ID " +
            "left join LIKES as L on F.FILM_ID = L.FILM_ID " +
            "group by F.FILM_ID, F.FILM_NAME, F.DESCRIPTION, F.RELEASE_DATE, F.DURATION, M.MPA_ID, M.MPA_NAME, " +
            "G.GENRE_ID, G.GENRE_NAME " +
            "order by LIKES desc, F.FILM_NAME " +
            "limit ?";

    static final String DELETE_FILM_GENRES = "delete from FILM_GENRE where FILM_ID = ?";

    static final String LIKE_FILM = "insert into LIKES values(?, ?)";

    static final String DELETE_LIKE_FROM_FILM = "delete from LIKES where FILM_ID = ? AND USER_ID = ?";

    static final String ADD_GENRE = "insert into FILM_GENRE values(?, ?)";
}