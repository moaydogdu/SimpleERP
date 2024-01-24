package org.example.simpleerp.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CustomError {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    /**
     * The httpStatus of the error response.
     */
    private HttpStatus httpStatus;

    /**
     * The header of the error response.
     */
    private String header;

    /**
     * The message describing the error.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * Indicates if the API call was successful or not.
     */
    @Builder.Default
    private final Boolean isSuccess = false;

    /**
     * List of sub-errors.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubError> subErrors;

    /**
     * SubError class represents a sub-error with its details.
     */
    @Getter
    @Builder
    public static class SubError {
        /**
         * The error message.
         */
        private String message;
        /**
         * The field that caused the error.
         */
        private String field;
        /**
         * The value of the field that caused the error.
         */
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object value;
        /**
         * The type of the error.
         */
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String type;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Header {
        /**
         * API_ERROR header.
         */
        API_ERROR("API ERROR"),
        /**
         * ALREADY_EXIST header.
         */
        ALREADY_EXIST("ALREADY EXIST"),
        /**
         * NOT_FOUND header.
         */
        NOT_FOUND("NOT EXIST"),
        /**
         * VALIDATION_ERROR header.
         */
        VALIDATION_ERROR("VALIDATION ERROR"),
        /**
         * DATABASE_ERROR header.
         */
        DATABASE_ERROR("DATABASE ERROR"),
        /**
         * PROCESS_ERROR header.
         */
        PROCESS_ERROR("PROCESS ERROR"),
        /**
         * AUTH_ERROR header.
         */
        AUTH_ERROR("AUTH ERROR");

        /**
         * The name of the header.
         */
        private final String name;
    }
}
