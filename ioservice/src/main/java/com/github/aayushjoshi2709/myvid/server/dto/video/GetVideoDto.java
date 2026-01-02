package com.github.aayushjoshi2709.myvid.server.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetVideoDto extends CreateVideoDto {
    private UUID id;
}
