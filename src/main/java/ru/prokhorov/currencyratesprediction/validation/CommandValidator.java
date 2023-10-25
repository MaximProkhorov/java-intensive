package ru.prokhorov.currencyratesprediction.validation;

import org.springframework.stereotype.Service;
import ru.prokhorov.currencyratesprediction.enumeration.CommandType;
import ru.prokhorov.currencyratesprediction.enumeration.Currency;
import ru.prokhorov.currencyratesprediction.enumeration.PredictionType;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CommandValidator {

    private static final int WORDS_IN_COMMAND = 3;

    public void validateCommandLength(String[] words) {
        if (words.length != WORDS_IN_COMMAND) {
            throw new ValidationException("Запрос составлен неправильно");
        }
    }

    public CommandType validateCommandType(String commandTypeName) {
        try {
            return CommandType.fromName(commandTypeName);
        } catch (EnumConstantNotPresentException e) {
            throw new ValidationException("Команда с названием "
                    + commandTypeName + " не найдена. Возможные варианты: "
                    + Arrays.stream(CommandType.values()).map(CommandType::getName).collect(Collectors.toList()));
        }
    }

    public Currency validateCurrency(String currencyCode) {
        try {
            return Currency.fromCode(currencyCode.toUpperCase());
        } catch (EnumConstantNotPresentException e) {
            throw new ValidationException("Валюта с кодом "
                    + currencyCode + " не найдена. Возможные варианты: "
                    + Arrays.stream(Currency.values()).map(Currency::getCode).collect(Collectors.toList()));
        }
    }

    public PredictionType validatePredictionType(String predictionTypeName) {
        try {
            return PredictionType.fromName(predictionTypeName);
        } catch (EnumConstantNotPresentException e) {
            throw new ValidationException("Тип предсказания с названием "
                    + predictionTypeName + " не найден. Возможные варианты: "
                    + Arrays.stream(PredictionType.values()).map(PredictionType::getName).collect(Collectors.toList()));
        }
    }

}
