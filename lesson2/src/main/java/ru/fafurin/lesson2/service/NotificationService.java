package ru.fafurin.lesson2.service;

import org.springframework.stereotype.Service;
import ru.fafurin.lesson2.domain.User;

@Service
public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }
}
