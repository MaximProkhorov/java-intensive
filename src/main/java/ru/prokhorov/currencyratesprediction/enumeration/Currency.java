package ru.prokhorov.currencyratesprediction.enumeration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Currency {

    EUR("EUR", "Евро"),
    USD("USD", "Доллар США"),
    TRY("TRY", "Турецкая лира");

    private final String code;
    private final String name;

    Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Currency fromCode(String code) {
        return Arrays.stream(Currency.values())
                .filter(currency -> currency.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Currency.class, code));
    }
}
