package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    @Override
    public User deleteUser(User user) {
        return userStorage.deleteUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userStorage.getUser(id);
    }

    @Override
    public List<User> getListUsers() {
        return userStorage.getListUsers();
    }

    public User addFriend(Integer id, Integer friendId) {
        User user = userStorage.getUser(id);
        User friend = userStorage.getUser(friendId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        if (friend == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        user.getFriendsId().add(friendId);
        friend.getFriendsId().add(id);

        userStorage.updateUser(friend);
        return userStorage.updateUser(user);
    }

    public User delFriend(Integer userId, Integer friendId) {
        User user = userStorage.getUser(userId);
        User removeFriend = userStorage.getUser(friendId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        if (removeFriend == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        user.getFriendsId().remove(friendId);
        removeFriend.getFriendsId().remove(userId);

        userStorage.updateUser(removeFriend);
        return userStorage.updateUser(user);
    }

    public List<User> getCommonFriends(Integer userId, Integer otherId) {
        User user = userStorage.getUser(userId);
        User otherUser = userStorage.getUser(otherId);

        if (user == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        if (otherUser == null) {
            throw new UserNotFoundException("Такого пользователя нет");
        }

        return user.getFriendsId()
                .stream()
                .filter(otherUser.getFriendsId()::contains)
                .map(userStorage::getUser)
                .collect(Collectors.toList());
    }

    public List<User> getAllFriends(Integer userId) {
        return userStorage.getUser(userId).getFriendsId()
                .stream()
                .map(this::getUser)
                .collect(Collectors.toList());
    }
}