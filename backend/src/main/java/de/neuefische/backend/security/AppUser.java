package de.neuefische.backend.security;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record AppUser(
        String id,
        String username,
        String email,
        String password,
        AppUserRole role
) {
}