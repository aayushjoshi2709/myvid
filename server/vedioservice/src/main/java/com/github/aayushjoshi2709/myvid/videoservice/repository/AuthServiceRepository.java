package com.github.aayushjoshi2709.myvid.videoservice.repository;

import com.github.aayushjoshi2709.myvid.videoservice.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Repository
public class AuthServiceRepository {

    @Value("${services.auth}")
    private String authServiceUrl;

    public UserDto getUserDetailsById(HttpHeaders headers, UUID userId) {
        String userIdStr = userId.toString();
        return RestClient.builder().baseUrl(authServiceUrl).build().get()
                .uri("/api/v1/user/{id}", userIdStr)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .body(UserDto.class);
    }
}
