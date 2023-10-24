package ru.prokhorov.currencyratesprediction.service;

import org.springframework.stereotype.Service;
import ru.prokhorov.currencyratesprediction.domain.Rate;
import ru.prokhorov.currencyratesprediction.enumeration.PredictionType;
import ru.prokhorov.currencyratesprediction.util.MathUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatePredictor {

    private static final int DEPTH = 7;

    public List<Rate> predict(PredictionType predictionType, List<Rate> rates) {
        LocalDate currentDate = LocalDate.now();
        LocalDate goalDate = currentDate.plusDays(predictionType.getDays());
        LocalDate lastDateFromFile = rates.get(0).getDate();

        return calculateFutureRates(goalDate, lastDateFromFile, rates);

    }

    private List<Rate> calculateFutureRates(LocalDate goalDate, LocalDate lastDateFromFile, List<Rate> rates) {
        rates = rates.subList(0, DEPTH);
        Collections.reverse(rates);
        List<Rate> ratesLinked = new LinkedList<>(rates);

        int amount = rates.get(0).getAmount();
        String currencyName = rates.get(0).getCurrencyName();

        for (LocalDate date = lastDateFromFile.plusDays(1); !date.isAfter(goalDate); date = date.plusDays(1)) {
            BigDecimal average = MathUtil.average(ratesLinked.stream().map(Rate::getExchangeRate).collect(Collectors.toList()));

            ratesLinked.remove(0);
            ratesLinked.add(new Rate(amount, date, average, currencyName));
        }
        return ratesLinked;
    }
}
