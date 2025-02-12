package org.company.feignclientdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum ExceptionMessage {
    UNEXPECTED_ERROR("An unexpected error occurred. Please try again later."),

    CURRENCY_NOT_FOUND("User not found."),

    CONFLICT("Username already exists."),

    INVALID_ACCOUNT_DETAILS("Invalid account details");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

}
