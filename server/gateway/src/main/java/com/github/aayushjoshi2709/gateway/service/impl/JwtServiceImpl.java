package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.service.JwtService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public UUID getUserId(String token) {
        return null;
    }

    @Override
    public ArrayList<String> getClaims(String token) {
        return new ArrayList<>();
    }
}
