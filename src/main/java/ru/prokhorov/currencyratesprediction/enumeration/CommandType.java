package ru.prokhorov.currencyratesprediction.enumeration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CommandType {

    RATE("rate");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public static CommandType fromName(String name) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(CommandType.class, name));
    }
}
