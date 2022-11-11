package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    private Integer id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Size(min = 1, max = 200)
    private String description;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительной")
    private Integer duration;

    private Set<Integer> likes = new HashSet<>();
}