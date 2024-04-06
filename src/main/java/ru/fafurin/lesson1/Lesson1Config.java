package ru.fafurin.lesson1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.fafurin.lesson1.repository.UserDBRepository;
import ru.fafurin.lesson1.repository.UserFileRepository;
import ru.fafurin.lesson1.repository.UserRepository;

@Configuration
public class Lesson1Config {
    @Bean("DBRepo")
    UserRepository getUserDBRepository() {
        return new UserDBRepository();
    }

    @Bean("FileRepo")
    @Primary
    UserRepository getUserFileRepository() {
        return new UserFileRepository();
    }
}
