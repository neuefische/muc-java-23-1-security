package de.neuefische.frontend;

public record AppUserRequest(
        String username,
        String email,
        String password
) {
}
