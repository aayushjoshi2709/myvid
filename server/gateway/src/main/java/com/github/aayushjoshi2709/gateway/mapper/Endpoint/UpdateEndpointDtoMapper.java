
package com.github.aayushjoshi2709.gateway.mapper.Endpoint;

import org.mapstruct.Mapper;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

@Mapper(componentModel = "spring")
public interface UpdateEndpointDtoMapper {
  UpdateEndpointDto toDto(Endpoint ed);

  Endpoint toEntity(UpdateEndpointDto ued);
}
