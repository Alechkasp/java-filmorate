package ru.yandex.practicum.filmorate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final HashMap<Integer, User> users = new HashMap<>();
    public static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> findAll() {
        log.debug("Получен запрос GET /users.");

        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("Получен запрос POST /users.");

        if (!user.getEmail().contains("@")) {
            log.error("Электронная почта должна содержать символ @");
            throw new ValidationException("Электронная почта должна содержать символ @");
        } else if (user.getLogin().contains(" ")) {
            log.error("Логин не может содержать пробелы");
            throw new ValidationException("Логин не может содержать пробелы");
        } else if (user.getName().isEmpty()) {
            log.debug("Имя пустое, будет использован логин");
            user.setName(user.getName());
        } else if (user.getBirthday().isAfter(CURRENT_DATE)) {
            log.error("Дата рождения не может быть в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        } else {
            users.put(user.getId(), user);
            log.debug("Пользователь успешно создан!");
        }
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        log.debug("Получен запрос PUT /users.");

        if (!users.containsKey(user.getId())) {
            log.error("Такого пользователя нет");
            throw new ValidationException("Такого пользователя нет");
        } else if (!user.getEmail().contains("@")) {
            log.error("Электронная почта должна содержать символ @");
            throw new ValidationException("Электронная почта должна содержать символ @");
        } else if (user.getLogin().contains(" ")) {
            log.error("Логин не может содержать пробелы");
            throw new ValidationException("Логин не может содержать пробелы");
        } else if (user.getName().isEmpty()) {
            log.debug("Имя пустое, будет использован логин");
            user.setName(user.getName());
        } else if (user.getBirthday().isAfter(CURRENT_DATE)) {
            log.error("Дата рождения не может быть в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        } else {
            users.get(user.getId()).setEmail(user.getEmail());
            users.get(user.getId()).setLogin(user.getLogin());
            users.get(user.getId()).setName(user.getName());
            users.get(user.getId()).setBirthday(user.getBirthday());
            log.debug("Пользователь успешно обновлен!");
        }
        return user;
    }
}