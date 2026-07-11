package com.cortinovis.clients.exceptions;

public class CsvReadException extends RuntimeException{
    public CsvReadException(String message,  Throwable cause) {
      super(message, cause);
    }
}
