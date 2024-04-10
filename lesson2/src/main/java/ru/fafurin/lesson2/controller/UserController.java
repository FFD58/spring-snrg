package ru.fafurin.lesson2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fafurin.lesson2.domain.User;
import ru.fafurin.lesson2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> userList() {
        return service.getUserRepository().getUsers();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        service.processRegistration(user);
        return "User added from body!";
    }
}
