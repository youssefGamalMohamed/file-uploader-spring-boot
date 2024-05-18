package org.youssef.gamal.file_uploader.app.exceptions;

import jakarta.servlet.MultipartConfigElement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class RestExceptionHandler {


    private final MultipartConfigElement multipartConfigElement;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(Exception e) {
        e.printStackTrace();
        log.error("Unhandled Exception = {} , Class Name of the Exception = {} ", e , e.getClass().getName());
        return ResponseEntity.internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNoSuchFieldException(NoSuchElementException e) {
        log.error("NoSuchElementException = ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException = ", e);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponse validationErrorResponse = ValidationErrorResponse.builder()
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(validationErrorResponse);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseEntity<ApiErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ApiErrorResponse maxSizeExceededErrorResponse = MaximumUploadSizeExceededErrorResponse.builder()
                .message("uploaded files should not be greater than " + multipartConfigElement.getMaxRequestSize() + " bytes")
                .fileMaximumAllowedSize(multipartConfigElement.getMaxFileSize() + "bytes")
                .maximumAllowedRequestSize(multipartConfigElement.getMaxRequestSize() + "bytes")
                .build();

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(maxSizeExceededErrorResponse);
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ApiErrorResponse> handleUnsupportedMediaTypeException(UnsupportedMediaTypeStatusException e) {

        ApiErrorResponse unSupportedMediaTypeErrorResponse = UnSupportedMediaTypeErrorResponse.builder()
                .message("Unsupported Media Type => " + e.getContentType())
                .rejectedContentType(e.getContentType() == null ? "null" : e.getContentType().toString())
                .supportedTypes(
                        e.getSupportedMediaTypes()
                                .stream()
                                .map(MediaType::toString)
                                .toList()
                )
                .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(unSupportedMediaTypeErrorResponse);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleNoResourceFoundException(MissingServletRequestParameterException e) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message("Missing Parameter => " + e.getParameterName())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }


    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message("Resource Not Found => " + e.getHttpMethod().name() + " /" + e.getResourcePath())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }

}
