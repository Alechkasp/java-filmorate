package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@With
@AllArgsConstructor
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

    @NotNull(message = "Mpa не может быть пустым")
    private Mpa mpa;

    private List<Genre> genres;

    public Film(Integer id, String name, String description, LocalDate releaseDate, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}