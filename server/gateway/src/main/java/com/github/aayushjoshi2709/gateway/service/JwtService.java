package com.github.aayushjoshi2709.gateway.service;

import java.util.ArrayList;
import java.util.UUID;

public interface JwtService {
    UUID getUserId(String token);
    ArrayList<String> getClaims(String token);
}
