package de.neuefische.backend.security;

public record AppUserRequest(
        String username,
        String email,
        String password
) {
}
