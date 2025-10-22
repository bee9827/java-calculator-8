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

        List<Integer> numbers = splitNumbers(inputView.readCombinedNumbers(), separator);
        PositiveNumberCalculator calculator = new PositiveNumberCalculator(numbers);
        Integer sum = calculator.sum();
        outputView.printlnSum(sum);

        inputView.close();
    }

    private List<Integer> splitNumbers(String combinedNumbers, NumberSeparator separator) {
        List<String> customSeparator = NumberSeparator.extractCustomSeparators(combinedNumbers);
        String numbersWithSeparator = NumberSeparator.extractNumbersWithSeparators(combinedNumbers);

        customSeparator.forEach(separator::addSeparator);
        return separator.splitNumbers(numbersWithSeparator);
    }
}
