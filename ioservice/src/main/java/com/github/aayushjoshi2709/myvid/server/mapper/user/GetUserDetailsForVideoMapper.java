package com.github.aayushjoshi2709.myvid.server.mapper.user;

import com.github.aayushjoshi2709.myvid.server.dto.user.GetUserDetailsForVideo;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserDetailsForVideoMapper {
    GetUserDetailsForVideo toDto(User user);
    User toEntity(GetUserDetailsForVideo getUserDetailsForVideo);
}
