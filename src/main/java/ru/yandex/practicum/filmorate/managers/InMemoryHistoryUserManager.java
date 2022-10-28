package ru.yandex.practicum.filmorate.managers;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryUserManager implements UserManager {
    private final HashMap<Integer, User> users = new HashMap<>();
    int id = 0;

    @Override
    public User addUser(User user) {
        if ((user.getName() == null) || (user.getName().equals(""))) {
            user.setName(user.getLogin());
        }
        id++;
        user.setId(id);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        if (users.containsKey(user.getId())) {
            user.setId(user.getId());
            users.put(user.getId(), user);
        } else {
            throw new ValidationException("Такого пользователя нет");
        }
    }

    @Override
    public List<User> getListUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public HashMap<Integer, User> getTableUsers() {
        return users;
    }
}