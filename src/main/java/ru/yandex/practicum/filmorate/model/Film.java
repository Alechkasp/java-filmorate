package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class Film {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String description;
    @JsonSerialize
    @JsonDeserialize
    private LocalDate releaseDate;
    private long duration;
}