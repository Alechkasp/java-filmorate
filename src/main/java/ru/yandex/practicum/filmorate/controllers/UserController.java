package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.services.InMemoryHistoryUserService;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService = new InMemoryHistoryUserService();

    @GetMapping
    public List<User> findAll() {
        log.debug("Получен запрос GET /users.");

        return userService.getListUsers();
    }

    @PostMapping
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
}