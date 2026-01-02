package com.github.aayushjoshi2709.myvid.server.mapper.user;

import com.github.aayushjoshi2709.myvid.server.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {
    UpdateUserDto toDto(User user);
    User toEntity(UpdateUserDto updateUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUserDto updateUserDto,
                           @MappingTarget User user);
}
