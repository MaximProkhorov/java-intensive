package ru.prokhorov.currencyratesprediction.validation;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
