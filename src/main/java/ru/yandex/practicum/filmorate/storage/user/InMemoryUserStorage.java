package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    @Override
    public User addUser(User user) {
        if ((user.getName() == null) || (user.getName().isBlank())) {
            user.setName(user.getLogin());
        }
        if (!user.getLogin().contains(" ")) {
            id++;
            user.setId(id);
            users.put(user.getId(), user);
        } else {
            throw new ValidationException("Логин не может содержать пробелы");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            user.setId(user.getId());
            users.put(user.getId(), user);
        } else {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        return user;
    }

    @Override
    public User deleteUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new UserNotFoundException("Такого пользователя нет");
        } else {
            User remoteUser = users.get(user.getId());
            users.remove(user.getId());
            return remoteUser;
        }
    }

    @Override
    public User getUser(Integer id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("Такого пользователя нет");
        } else {
            return users.get(id);
        }
    }

    @Override
    public List<User> getListUsers() {
        return new ArrayList<>(users.values());
    }
}