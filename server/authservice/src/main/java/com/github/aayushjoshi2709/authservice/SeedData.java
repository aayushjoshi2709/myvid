package com.github.aayushjoshi2709.authservice;


import com.github.aayushjoshi2709.authservice.entity.Role;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.repository.RoleRepository;
import com.github.aayushjoshi2709.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${appdata.defaults.adminPassword}")
    private String adminPassword = "";

    @Value("${appdata.defaults.adminEmail}")
    private String adminEmail = "";

    @Override
    public void run(String @NonNull ... args) throws Exception {
        if(this.userRepository.count() == 0){

            if(adminEmail.isEmpty() || adminPassword.isEmpty()){
                throw new RuntimeException("Admin email or password now round in config");
            }

            Role adminRole = new Role("ADMIN", "A role with all privileges");
            Role userRole = new Role("USER", "A role with all privileges");

            Role savedAdminRole = this.roleRepository.save(adminRole);
            Role savedUserRole = this.roleRepository.save(userRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail(adminEmail);
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRoles(List.of(savedAdminRole, savedUserRole));
            userRepository.save(admin);

            System.out.println("Database successfully seeded with BCrypt encrypted profiles.");
        } else {
            System.out.println("Database already contains data seeding skipped...");
        }
    }
}
