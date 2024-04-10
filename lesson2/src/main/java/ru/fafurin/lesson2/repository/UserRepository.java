package ru.fafurin.lesson2.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fafurin.lesson2.domain.User;
import ru.fafurin.lesson2.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, new UserMapper());
    }

    public User save(User user) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        return user;
    }
}
