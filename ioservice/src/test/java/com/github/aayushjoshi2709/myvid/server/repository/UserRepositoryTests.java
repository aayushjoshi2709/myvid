package com.github.aayushjoshi2709.myvid.server.repository;

import com.github.aayushjoshi2709.myvid.server.entity.User;
import com.github.aayushjoshi2709.myvid.server.entity.enums.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryFindAllReturnSavedUser(){
        User user = User
                .builder()
                .email("temp@gmail.com")
                .phoneNo(916253673L)
                .status(UserStatus.ACTIVE)
                .username("user")
                .password("pass")
                .build();
        User savedUser = this.userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
    }
}
