package com.github.aayushjoshi2709.gateway.entity.common;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import com.github.aayushjoshi2709.gateway.entity.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Common {
  @Id
  UUID id;

  @LastModifiedDate
  @Column("updatedAt")
  private LocalDateTime updatedAt;

  @CreatedDate
  @Column("createdAt")
  private LocalDateTime createdAt;

  @Builder.Default
  @Column("status")
  private Status status = Status.ACTIVE;
}
