package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.services.InMemoryHistoryUserService;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final InMemoryHistoryUserService userService = new InMemoryHistoryUserService();

    @GetMapping
    public List<User> findAll() {
        log.debug("Получен запрос GET /users.");

        return userService.getListUsers();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.debug("Получен запрос POST /users.");

        userService.addUser(user);
        log.debug("Пользователь успешно создан!");

        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.debug("Получен запрос PUT /users.");

        userService.updateUser(user);
        log.debug("Пользователь успешно обновлен!");

        return user;
    }
}