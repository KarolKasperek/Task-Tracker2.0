package com.taskTracker.enums;

public enum UserPermission {
    EDIT_WORKBENCH("EDIT_WORKBENCH"),
    SECURITY_CONFIGURATION_ACCESS("SECURITY_CONFIGURATION_ACCESS");

    private final String value;

    UserPermission(String value) {
        this.value = value;
    }
}
