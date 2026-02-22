package com.mapnaom.foodapp;



import com.mapnaom.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMockDataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadMockUsers() {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> users = List.of(
                        User.builder()
                                .firstname("Admin")
                                .lastname("User")
                                .email("admin@example.com")
                                .password(passwordEncoder.encode("admin1234"))
                                .role(Role.ADMIN)
                                .build(),
                        User.builder()
                                .firstname("Regular")
                                .lastname("User")
                                .email("user@example.com")
                                .password(passwordEncoder.encode("user1234"))
                                .role(Role.USER)
                                .build()
                );
                userRepository.saveAll(users);
                System.out.println("Mock users created.");
            }
        };
    }
}

