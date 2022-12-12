package ru.yandex.practicum.filmorate;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    private InMemoryUserStorage userService;
    private User user;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        userService = new InMemoryUserStorage();
        user = new User();

        user.setId(1);
        user.setEmail("peter.petrov@yandex.ru");
        user.setLogin("peter235");
        user.setName("Петр");
        user.setBirthday(LocalDate.of(1995, 11, 23));
    }

    @Test
    void emailMustContainsSymbolDog() {
        user.setEmail("peter.petrov.yandex.ru");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emailNull() {
        user.setEmail(null);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void loginWithNull() {
        user.setLogin(null);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void loginEmpty() {
        user.setLogin("");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void loginWithOnlyBlank() {
        user.setLogin(" ");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void loginWithBlank() {
        user.setLogin("Nick Name");

        assertThrows(ValidationException.class, () -> userService.addUser(user));
    }

    @Test
    void birthdayIsAfterNow() {
        user.setBirthday(LocalDate.of(2023, 11, 10));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
}*/