package calculator;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CalculatorSeparator separator = new CalculatorSeparator(DefaultSeparator.getDefaultSeparators());

        addPositiveNumbersBySeparator(separator);

        inputView.close();
    }

    private void addPositiveNumbersBySeparator(CalculatorSeparator separator) {
        List<Integer> splitNumbers = separator.splitNumbers(inputView.readPlusString());
        splitNumbers.forEach(Validator::validatePositive);
        int result = Calculator.addNumbers(splitNumbers);
        outputView.printlnResult(result);
    }
}
