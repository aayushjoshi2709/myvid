package com.github.aayushjoshi2709.gateway.mapper.Service;

import org.mapstruct.Mapper;
import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.dto.Service.UpdateServiceDto;

@Mapper(componentModel = "spring")
public interface UpdateServiceDtoMapper {
  UpdateServiceDto toDto(Service s);

  Service toEntity(UpdateServiceDto usd);
}
