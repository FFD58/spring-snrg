package ru.fafurin.lesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson1.domain.User;
import ru.fafurin.lesson1.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        return userRepository.getUser();
    }
}
