package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserServiceInterface;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceInterface userService;

    @GetMapping
    public List<User> findAll() {
        log.debug("Получен запрос GET /users.");

        return userService.getListUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        log.debug("Получен запрос GET /users/{id}");

        return userService.getUser(id);
    }

    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@PathVariable Integer id) {
        log.debug("Получен запрос GET /users/{id}/friends");

        return userService.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        log.debug("Получен запрос GET /users/{id}/friends/common/{otherId}");

        return userService.getCommonFriends(id, otherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        log.debug("Получен запрос POST /users.");

        User addedUser = userService.addUser(user);
        log.debug("Пользователь успешно создан!");

        return addedUser;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.debug("Получен запрос PUT /users.");

        User updatedUser = userService.updateUser(user);
        log.debug("Пользователь успешно обновлен!");

        return updatedUser;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.debug("Получен запрос PUT /users/{id}/friends/{friendId}");

        User user = userService.addFriend(id, friendId);
        log.debug("Друг добавлен!");

        return user;
    }

    @DeleteMapping
    public User deleteUser(@Valid @RequestBody User user) {
        log.debug("Получен запрос DELETE /users.");

        User removeUser = userService.deleteUser(user);
        log.debug("Пользователь успешно удален!");

        return removeUser;
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User delFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.debug("Получен запрос DELETE /users/{id}/friends/{friendId}");

        User delFriend = userService.delFriend(id, friendId);
        log.debug("Друг успешно удален!");

        return delFriend;
    }
}