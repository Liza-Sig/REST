package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if ("admin".equals(user) && "123".equals(password)) {
            return List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        }return List.of();
    }
}
