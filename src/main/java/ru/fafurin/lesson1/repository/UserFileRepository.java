package ru.fafurin.lesson1.repository;

import ru.fafurin.lesson1.domain.User;

public class UserFileRepository implements UserRepository {

    public User getUser() {
        System.out.println("Return user from file");
        return new User();
    }
}
