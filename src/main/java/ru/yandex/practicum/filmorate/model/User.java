package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Integer id;

    @Email(message = "Email должен быть корректным адресом электронной почты")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    private LocalDate birthday;

    private Set<Integer> friendsId = new HashSet<>();
}