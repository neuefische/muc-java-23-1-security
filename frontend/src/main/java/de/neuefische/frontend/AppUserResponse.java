package de.neuefische.frontend;

public record AppUserResponse(
        String id,
        String username,
        String email,
        String role
) {
}
