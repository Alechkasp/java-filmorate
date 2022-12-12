package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FieldValidationException;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    public UserServiceImpl(@Qualifier("dbUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public List<User> getAll() {
        return userStorage.getListUsers();
    }

    @Override
    public User getUser(Integer id) {
        User user = userStorage.getUser(id);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        return user;
    }

    @Override
    public User addUser(User newUser) {
/*        if (emailIsBusy(newUser.getEmail())) {
            throw new FieldValidationException("email", "User with this email is already exists");
        }*/

        checkUserName(newUser);

        return userStorage.addUser(newUser);
    }

    @Override
    public User updateUser(User user) {
        if (userStorage.getUser(user.getId()) == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

/*        if (emailIsBusy(user.getEmail())) {
            throw new FieldValidationException("email", "User with this email is already exists");
        }*/

        checkUserName(user);

        return userStorage.updateUser(user.getId(), user);
    }

    @Override
    public User delUser(Integer id) {
        User user = userStorage.delUser(id);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        return user;
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        if (userId == friendId) {
            throw new ValidationException("Одинаковые идентификаторы");
        }

        User user = userStorage.getUser(userId);
        User friend = userStorage.getUser(friendId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        if (friend == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        userStorage.addFriend(userId, friendId);
    }

    @Override
    public void delFriend(Integer userId, Integer friendId) {
        User user = userStorage.getUser(userId);
        User friend = userStorage.getUser(friendId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        if (friend == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        userStorage.delFriend(userId, friendId);
    }

    @Override
    public List<User> getFriends(Integer id) {
        User user = userStorage.getUser(id);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        return userStorage.getAllFriends(id);
    }

    @Override
    public List<User> getCommonFriends(Integer userId, Integer otherUserId) {
        User user = userStorage.getUser(userId);
        User otherUser = userStorage.getUser(otherUserId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        if (otherUser == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        return userStorage.getCommonFriends(userId, otherUserId);
    }

    private boolean emailIsBusy(String email) {
        return userStorage.getByEmail(email) != null;
    }

    private void checkUserName(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }



}