package ru.fafurin.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.fafurin.lesson1.service.UserService;

@SpringBootApplication
public class Lesson1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Lesson1Application.class, args);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUser());
    }
}
