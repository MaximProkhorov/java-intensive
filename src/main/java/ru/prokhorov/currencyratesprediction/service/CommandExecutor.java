package ru.prokhorov.currencyratesprediction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.prokhorov.currencyratesprediction.domain.Rate;
import ru.prokhorov.currencyratesprediction.enumeration.CommandType;
import ru.prokhorov.currencyratesprediction.enumeration.Currency;
import ru.prokhorov.currencyratesprediction.enumeration.PredictionType;
import ru.prokhorov.currencyratesprediction.validation.CommandValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandExecutor {

    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int CURRENCY_INDEX = 1;
    private static final int PREDICTION_TYPE_INDEX = 2;

    private final CommandValidator commandValidator;
    private final RatePredictor ratePredictor;
    private final CsvReaderResultStorage csvReaderResultStorage;

    public void execute(String command) {

        String[] words = command.split(COMMAND_SPLITTER);

        commandValidator.validateCommandLength(words);

        CommandType commandType = commandValidator.validateCommandType(words[COMMAND_TYPE_INDEX]);
        Currency currency = commandValidator.validateCurrency(words[CURRENCY_INDEX]);
        PredictionType predictionType = commandValidator.validatePredictionType(words[PREDICTION_TYPE_INDEX]);

        if (commandType == CommandType.RATE) {
            executeRateCommand(currency, predictionType);
        }
    }

    private void executeRateCommand(Currency currency, PredictionType predictionType) {
        List<Rate> predictedRates = ratePredictor.predict(predictionType,
                csvReaderResultStorage.getRatesStorage().get(currency.getCode()));
        switch (predictionType) {
            case TOMORROW:
                printOutput(predictedRates.get(predictedRates.size() - 1));
                break;
            case WEEK:
                predictedRates.forEach(this::printOutput);
                break;
        }
    }

    private void printOutput(Rate rate) {
        System.out.println(rate);
    }
}
