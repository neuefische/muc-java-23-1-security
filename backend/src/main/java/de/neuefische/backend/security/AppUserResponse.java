package de.neuefische.backend.security;

public record AppUserResponse(
        String id,
        String username,
        String email,
        AppUserRole role
) {
}
