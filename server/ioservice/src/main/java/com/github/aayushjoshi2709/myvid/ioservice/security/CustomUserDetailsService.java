package com.github.aayushjoshi2709.myvid.ioservice.security;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.aayushjoshi2709.myvid.ioservice.entity.User;
import com.github.aayushjoshi2709.myvid.ioservice.entity.UserPrinciple;
import com.github.aayushjoshi2709.myvid.ioservice.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user =  this.userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("Username not found")
        );
        return new UserPrinciple(user.getUsername(), user.getPassword());
    }
}
