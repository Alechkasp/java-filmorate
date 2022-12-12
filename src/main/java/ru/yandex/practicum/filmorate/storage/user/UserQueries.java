package ru.yandex.practicum.filmorate.storage.user;

public class UserQueries {
    static final String GET_ALL = "select * from USERS";

    static final String GET_BY_ID = "select * from USERS where USER_ID = ?";

    static final String GET_BY_EMAIL = "select * from USERS where EMAIL = ?";

    static final String update = "update USERS " +
            "set " +
            "EMAIL = ?, " +
            "LOGIN = ?, " +
            "USER_NAME = ?, " +
            "BIRTHDAY = ? " +
            "where USER_ID = ?";

    static final String DELETE = "delete from USERS where USER_ID = ?";

    static final String GET_FRIENDS = "select " +
            "U.USER_ID, " +
            "U.EMAIL, " +
            "U.LOGIN, " +
            "U.USER_NAME, " +
            "U.BIRTHDAY " +
            "from FRIENDSHIP as F " +
            "join USERS as U on U.USER_ID = F.FRIEND_ID " +
            "where F.USER_ID = ?";

    static final String GET_COMMON_FRIENDS = "select " +
            "U.USER_ID, " +
            "U.EMAIL, " +
            "U.LOGIN, " +
            "U.USER_NAME, " +
            "U.BIRTHDAY " +
            "from FRIENDSHIP as F " +
            "join USERS AS U on U.USER_ID = F.FRIEND_ID " +
            "where F.USER_ID = ? and " +
            "F.FRIEND_ID in (" +
            "select FRIEND_ID " +
            "from FRIENDSHIP " +
            "where USER_ID = ?)";

    static final String DELETE_FRIEND = "delete " +
            "from FRIENDSHIP " +
            "where " +
            "USER_ID = ? and FRIEND_ID = ? ";
}
