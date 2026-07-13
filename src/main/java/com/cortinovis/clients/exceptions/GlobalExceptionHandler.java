package com.cortinovis.clients.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidClientException.class)
  public ResponseEntity<String> handleInvalidClientException(
          InvalidClientException ex) {

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
  }

  @ExceptionHandler(CsvReadException.class)
  public ResponseEntity<String> handleCsvReadException(
          CsvReadException ex) {

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.getMessage());
  }

  @ExceptionHandler(CsvWriteException.class)
  public ResponseEntity<String> handleCsvWriteException(CsvWriteException ex) {
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.getMessage());
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<String> handleNoHandlerFound(
          NoHandlerFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Endpoint not found");
  }
}