package com.taskTracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** An enumeration of messages that are displayed when throwing an exception. */
@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    FIELDS_NOT_FILLED("Mandatory fields are not filled in!"),
    FIELDS_NOT_CORRECT("Email or password is not correct!"),
    INVALID_DATE("Invalid date selected!"),
    TASK_DOES_NOT_EXIST("Task does not exist!"),
    USER_DOES_NOT_EXIST("User does not exist!"),
    NOT_FOUND("Not found!");

    private String exceptionText;
}
