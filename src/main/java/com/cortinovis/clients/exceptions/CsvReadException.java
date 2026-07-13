package com.cortinovis.clients.exceptions;

public class CsvReadException extends RuntimeException {

  public CsvReadException(String message, Throwable cause) {
    super(message, cause);
  }

  public CsvReadException(String message) {
    super(message);
  }
}