package com.TaskTracker.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.TaskTracker.enums.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(SECURITY_CONFIGURATION_ACCESS, EDIT_WORKBENCH)),
    USER(Sets.newHashSet(EDIT_WORKBENCH));
    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
