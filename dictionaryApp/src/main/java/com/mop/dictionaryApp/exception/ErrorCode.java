
package com.mop.dictionaryApp.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    GLOSSARY_NOT_FOUND(HttpStatus.NOT_FOUND, "The glossary with the given ID does not exist"),
    WORD_NOT_FOUND(HttpStatus.NOT_FOUND, "The word with the given ID does not exist"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "This user does not exist"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "The input provided is invalid"),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");

    private final HttpStatus httpStatus;
    private final String message;

    private ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
