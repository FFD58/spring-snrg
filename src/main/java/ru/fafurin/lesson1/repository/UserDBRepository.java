package ru.fafurin.lesson1.repository;

import ru.fafurin.lesson1.domain.User;

public class UserDBRepository implements UserRepository {

    public User getUser() {
        System.out.println("Return user from DB");
        return new User();
    }
}
