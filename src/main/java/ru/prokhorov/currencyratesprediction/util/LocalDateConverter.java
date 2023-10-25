package ru.prokhorov.currencyratesprediction.util;

import com.opencsv.bean.AbstractBeanField;
import ru.prokhorov.currencyratesprediction.domain.Rate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField<Rate, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    protected Object convert(String s) {
        return LocalDate.parse(s, FORMATTER);
    }
}
