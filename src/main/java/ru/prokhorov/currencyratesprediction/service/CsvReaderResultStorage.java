package ru.prokhorov.currencyratesprediction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.prokhorov.currencyratesprediction.domain.Rate;
import ru.prokhorov.currencyratesprediction.enumeration.Currency;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CsvReaderResultStorage {

    private final CsvReader csvReader;
    private final Map<String, List<Rate>> ratesStorage = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        for (Currency currency : Currency.values()) {
            ratesStorage.put(currency.getCode(), csvReader.read(currency));
        }
    }

    public Map<String, List<Rate>> getRatesStorage() {
        return ratesStorage;
    }
}
