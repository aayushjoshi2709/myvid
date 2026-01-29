package com.github.aayushjoshi2709.myvid.ioservice.dto.comment;

import java.time.LocalDateTime;
import java.util.UUID;

import com.github.aayushjoshi2709.myvid.ioservice.dto.user.GetUserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentDto {
    private UUID id;
    private String message;
    private LocalDateTime createdAt;
    private GetUserDto author;
    private UUID vedioId;
    private Integer replyCount;
}
