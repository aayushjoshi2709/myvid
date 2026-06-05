package com.github.aayushjoshi2709.gateway.mapper.Service;

import org.mapstruct.Mapper;
import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;

@Mapper(componentModel = "spring")
public interface CreateServiceDtoMapper {
  CreateServiceDto toDto(Service s);

  Service toEntity(CreateServiceDto csd);
}
