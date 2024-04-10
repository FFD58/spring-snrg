package ru.fafurin.lesson2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson2.domain.User;
import ru.fafurin.lesson2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void processRegistration(User user) {
        userRepository.save(createUpperCaseUser(user.getName(), user.getAge(), user.getEmail()));
        notificationService.notifyUser(user);
    }

    private User createUpperCaseUser(String name, Integer age, String email) {
        User user = new User();
        user.setName(toUpperCase(name));
        user.setAge(age);
        user.setEmail(toUpperCase(email));
        return user;
    }

    private User createLowerCaseUser(String name, Integer age, String email) {
        User user = new User();
        user.setName(toLowerCase(name));
        user.setAge(age);
        user.setEmail(toLowerCase(email));
        return user;
    }

    private String toLowerCase(String str) {
        return str.toLowerCase();
    }

    private String toUpperCase(String str) {
        return str.toUpperCase();
    }
}