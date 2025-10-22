package calculator;

import calculator.model.DefaultSeparator;
import calculator.model.NumberSeparator;
import calculator.model.PositiveNumberCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());

        PositiveNumberCalculator calculator = createCalculator(inputView.readCombinedNumbers(), separator);
        Integer sum = calculator.sum();
        outputView.printlnSum(sum);

        inputView.close();
    }

    private PositiveNumberCalculator createCalculator(String combinedNumbers, NumberSeparator separator) {
        List<String> customSeparator = NumberSeparator.extractCustomSeparators(combinedNumbers);
        String numbersWithSeparator = NumberSeparator.extractNumbersWithSeparators(combinedNumbers);
        
        customSeparator.forEach(separator::addSeparator);
        List<Integer> splitNumbers = separator.splitNumbers(numbersWithSeparator);

        return new PositiveNumberCalculator(splitNumbers);
    }
}
