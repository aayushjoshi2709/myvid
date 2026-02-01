package com.github.aayushjoshi2709.myvid.ioservice.mapper.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.UpdateUserDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {
    UpdateUserDto toDto(User user);
    User toEntity(UpdateUserDto updateUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUserDto updateUserDto,
                           @MappingTarget User user);
}
