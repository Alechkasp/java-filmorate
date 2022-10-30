package ru.yandex.practicum.filmorate.services;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void updateUser(User user);

    List<User> getListUsers();
}