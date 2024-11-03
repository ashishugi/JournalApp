package net.engineeringdigest.journalApp.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("User"),
    ADMIN("Admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
