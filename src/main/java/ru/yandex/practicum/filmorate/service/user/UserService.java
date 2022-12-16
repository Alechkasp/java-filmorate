package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUser(Integer id);
    User addUser(User user);
    User updateUser(User user);
    User delUser(Integer id);
    void addFriend(Integer userId, Integer friendId);
    void delFriend(Integer userId, Integer friendId);
    List<User> getFriends(Integer id);
    List<User> getCommonFriends(Integer userId, Integer otherUserId);
}
