package ru.prokhorov.currencyratesprediction.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.prokhorov.currencyratesprediction.util.LocalDateConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @CsvBindByName(column = "nominal")
    private int amount;
    @CsvCustomBindByName(column = "data", converter = LocalDateConverter.class)
    private LocalDate date;
    @CsvBindByName(column = "curs")
    private BigDecimal exchangeRate;
    @CsvBindByName(column = "cdx")
    private String currencyName;

    @Override
    public String toString() {
        return String.format("%s - %s (за %s)",
                date.format(DateTimeFormatter.ofPattern("E dd.MM.yyyy")),
                exchangeRate,
                amount);
    }
}
