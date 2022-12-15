package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaStorageTest {
    private final MpaStorage mpaStorage;

    @Test
    void shouldReturnSizeList() {
        assertThat(mpaStorage.getAll()).hasSize(5);
    }

    @Test
    void shouldReturnFirstMpa() {
        Mpa mpa = new Mpa(1, "G");
        assertThat(mpaStorage.getMpaId(1)).isEqualTo(mpa);
    }
}