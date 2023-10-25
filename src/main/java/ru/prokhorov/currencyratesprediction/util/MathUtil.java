package ru.prokhorov.currencyratesprediction.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MathUtil {

    public static BigDecimal average(List<BigDecimal> bigDecimals) {
        return bigDecimals.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(bigDecimals.size()), RoundingMode.HALF_UP);
    }
}
