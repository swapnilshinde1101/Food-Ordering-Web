package com.foodordering.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public enum USER_ROLE {
    ROLE_CUSTOMER,
    ROLE_RESTAURANT_OWNER,
    ROLE_ADMIN;

    // Convert string to enum value, throwing exception if not valid
    public static USER_ROLE getRole(String roleName) {
        for (USER_ROLE role : USER_ROLE.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        // Throw custom exception or log invalid role
        throw new IllegalArgumentException("Invalid role name: " + roleName);
    }

    // Default role
    public static USER_ROLE getRole() {
        return ROLE_CUSTOMER;
    }
}
