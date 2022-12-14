package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        log.debug("Получен запрос GET /users.");

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        log.debug("Получен запрос GET /users/{id}");

        return userService.getUser(id);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable Integer id) {
        log.debug("Получен запрос GET /users/{id}/friends");

        return userService.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        log.debug("Получен запрос GET /users/{id}/friends/common/{otherId}");

        return userService.getCommonFriends(id, otherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        log.debug("Получен запрос POST /users.");

        return userService.addUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        log.debug("Получен запрос PUT /users.");

        return userService.updateUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.debug("Получен запрос PUT /users/{id}/friends/{friendId}");

        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}")
    public User delUser(@PathVariable Integer id) {
        log.debug("Получен запрос DELETE /users.");

        return userService.delUser(id);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void delFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.delFriend(id, friendId);
    }
}