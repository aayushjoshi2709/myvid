package com.github.aayushjoshi2709.myvid.server.mapper.subscription;

import com.github.aayushjoshi2709.myvid.server.dto.subscription.GetSubscriptionDto;
import com.github.aayushjoshi2709.myvid.server.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetSubscriptionMapper {
    User toEntity(GetSubscriptionDto getSubscriptionDto);
    GetSubscriptionDto toDto(User user);
}
