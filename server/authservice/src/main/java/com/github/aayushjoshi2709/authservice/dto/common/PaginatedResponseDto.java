package com.github.aayushjoshi2709.authservice.dto.common;

public record PaginatedResponseDto <T>(Integer page, Integer limit, Integer numberOfPages,T data){}
