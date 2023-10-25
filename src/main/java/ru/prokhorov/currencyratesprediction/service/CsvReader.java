package ru.prokhorov.currencyratesprediction.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import ru.prokhorov.currencyratesprediction.domain.Rate;
import ru.prokhorov.currencyratesprediction.enumeration.Currency;

import java.io.*;
import java.util.List;

@Service
public class CsvReader {

    private static final String PATH = "csv/%s.csv";
    private static final char CSV_SEPARATOR = ';';

    public List<Rate> read(Currency currency) throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(String.format(PATH, currency.getCode()));
        List<Rate> rates;
        try (Reader reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            CsvToBean<Rate> csvToBean = new CsvToBeanBuilder<Rate>(reader)
                    .withType(Rate.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(CSV_SEPARATOR)
                    .build();
            rates = csvToBean.parse();
        }
        return rates;
    }
}
