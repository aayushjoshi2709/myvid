package com.github.aayushjoshi2709.myvid.ioservice.mapper.subscription;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.myvid.ioservice.dto.subscription.GetSubscriptionDto;
import com.github.aayushjoshi2709.myvid.ioservice.entity.User;

@Mapper(componentModel = "spring")
public interface GetSubscriptionMapper {
    User toEntity(GetSubscriptionDto getSubscriptionDto);
    GetSubscriptionDto toDto(User user);
}
