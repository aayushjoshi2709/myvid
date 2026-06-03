package com.github.aayushjoshi2709.gateway.mapper.Endpoint;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

@Mapper(componentModel = "spring")
public interface CreateEndpointDtoMapper {
  CreateEndpointDto toDto(Endpoint ed);

  Endpoint toEntity(CreateEndpointDto ced);
}
