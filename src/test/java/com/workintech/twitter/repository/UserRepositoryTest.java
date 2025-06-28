package com.workintech.twitter.repository;

import com.workintech.twitter.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    
    @DisplayName("Can find user by email")
    @Test
    void findByEmail() {
        User user = new User();
        user.setId(1L);
        user.setFullName("User");
        user.setNickName("UserNick");
        user.setEmail("user@user.com");
        user.setPassword("Test12345");
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
        
        User foundUser = userRepository.findByEmail("user@user.com").orElseThrow(() -> new AssertionError("User not found!"));

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getNickName(), foundUser.getNickName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }
}