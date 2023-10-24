package ru.prokhorov.currencyratesprediction.enumeration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PredictionType {

    TOMORROW("tomorrow", "Прогноз на завтра", 1),
    WEEK("week", "Прогноз на неделю, начиная с завтрашнего дня", 7);

    private final String name;
    private final String description;
    private final int days;

    PredictionType(String name, String description, int days) {
        this.name = name;
        this.description = description;
        this.days = days;
    }

    public static PredictionType fromName(String name) {
        return Arrays.stream(PredictionType.values())
                .filter(predictionType -> predictionType.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(PredictionType.class, name));
    }
}
