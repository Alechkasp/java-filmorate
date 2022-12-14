package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);
    User updateUser(Integer id, User user);
    User delUser(Integer id);
    User getUser(Integer id);
    List<User> getListUsers();
    User getByEmail(String email);
    void addFriend(Integer userId, Integer friendId);
    void delFriend(Integer userId, Integer friendId);
    List<User> getAllFriends(Integer id);
    List<User> getCommonFriends(Integer userId, Integer otherUserId);
}
