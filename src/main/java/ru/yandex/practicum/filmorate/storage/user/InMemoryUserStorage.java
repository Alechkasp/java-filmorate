package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("InMemoryUserStorage")
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
    public User updateUser(Integer id, User user) {
        if (users.containsKey(id)) {
            user.setId(id);
            users.put(id, user);
        } else {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        return user;
    }

    @Override
    public User delUser(Integer id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("Такого пользователя нет");
        } else {
            User remoteUser = users.get(id);
            users.remove(id);
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

    @Override
    public void addFriend(Integer userId, Integer friendId) {

    }

    @Override
    public void delFriend(Integer userId, Integer friendId) {

    }

    @Override
    public List<User> getAllFriends(Integer id) {
        return null;
    }

    @Override
    public List<User> getCommonFriends(Integer userId, Integer otherUserId) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}