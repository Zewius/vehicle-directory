package ru.zewius.web.vehicledirectory.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.zewius.web.vehicledirectory.exception.VehicleAlreadyExistException;
import ru.zewius.web.vehicledirectory.exception.VehicleNotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> vehicleNotFound(VehicleNotFoundException ex, WebRequest request) {
        log.info(request.toString());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .detail("Cannot find the vehicle record")
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(VehicleAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> vehicleAlreadyExist(VehicleAlreadyExistException ex, WebRequest request) {
        log.info(request.toString());
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .detail("Entry did not pass validation")
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> vehicleValidationFailed(ConstraintViolationException ex, WebRequest request) {
        log.info(request.toString());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .detail("Vehicle with this registration number already exist")
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
