package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;

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

        List<User> commonFriends = new ArrayList<>();
        for (Integer i : user.getFriendsId()) {
            if (otherUser.getFriendsId().contains(i)) {
                commonFriends.add(userStorage.getUser(i));
            }
        }
        return commonFriends;

    }

    public List<User> getAllFriends(Integer userId) {
        List<User> users = new ArrayList<>();
        for (User u : userStorage.getListUsers()) {
            if (userStorage.getUser(userId).getFriendsId().contains(u.getId())) {
                users.add(u);
            }
        }
        return users;
    }
}