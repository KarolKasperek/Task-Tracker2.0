package com.taskTracker.enums;

public enum VisibilityStatus {
    PRIVATE("private"),
    PUBLIC("public");

    private String statusValue;

    VisibilityStatus(String statusValue) {
        this.statusValue = statusValue;
    }
}
