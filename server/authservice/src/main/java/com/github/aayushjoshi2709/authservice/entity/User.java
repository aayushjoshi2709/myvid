package com.github.aayushjoshi2709.authservice.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.util.List;

import com.github.aayushjoshi2709.authservice.entity.common.Common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@DynamoDbBean
@Getter
@Setter
@Builder
public class User extends Common{
    private String name;
    private String username;
    private String email;
    private String password;
    private List<String> roles;

    @DynamoDbSecondaryPartitionKey(indexNames = "username-index")
    public String getUsername() {
        return username;
    }
}
