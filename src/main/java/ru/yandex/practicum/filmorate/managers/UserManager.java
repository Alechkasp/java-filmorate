package ru.yandex.practicum.filmorate.managers;

import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserManager {
    User addUser(User user);

    void updateUser(User user);

    List<User> getListUsers();

    HashMap<Integer, User> getTableUsers();
}
