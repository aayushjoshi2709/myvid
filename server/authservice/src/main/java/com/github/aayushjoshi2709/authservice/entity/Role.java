package com.github.aayushjoshi2709.authservice.entity;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@Getter
@Setter
@Builder
public class Role extends Common {
    private String name;
    private String description;
}
