package com.github.aayushjoshi2709.authservice.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.authservice.entity.Role;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class RoleRepository {
    private final DynamoDbTable<Role> roleTable;

    public RoleRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.roleTable = dynamoDbEnhancedClient.table("roles", TableSchema.fromBean(Role.class));
    }
    public Role save(Role role) {
        roleTable.putItem(role);
        return role;
    }

    public Optional<Role> findById(String id) {
        return Optional.ofNullable(roleTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }
}
