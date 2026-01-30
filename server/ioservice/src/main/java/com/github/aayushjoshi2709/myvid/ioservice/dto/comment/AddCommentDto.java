package com.github.aayushjoshi2709.myvid.ioservice.dto.comment;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentDto {
    @NotBlank(message = "Comment messages is required")
    @Size(min = 1, max = 300, message = "Comment message should of length 1 to 300")
    String message;

    UUID parentCommentId;
}
