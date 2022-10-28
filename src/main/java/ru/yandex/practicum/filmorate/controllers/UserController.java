package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.managers.InMemoryHistoryUserManager;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final InMemoryHistoryUserManager userManager = new InMemoryHistoryUserManager();

    @GetMapping
    public List<User> findAll() {
        log.debug("Получен запрос GET /users.");

        return userManager.getListUsers();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.debug("Получен запрос POST /users.");

        userManager.addUser(user);
        log.debug("Пользователь успешно создан!");

        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.debug("Получен запрос PUT /users.");

        userManager.updateUser(user);
        log.debug("Пользователь успешно обновлен!");

        return user;
    }
}