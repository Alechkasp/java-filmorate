package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class User {
    @NonNull
    private Integer id;
    @NonNull
    private String email;
    @NonNull
    private String login;
    private String name;
    @JsonSerialize
    @JsonDeserialize
    private LocalDate birthday;
}