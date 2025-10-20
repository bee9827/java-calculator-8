package calculator;

import calculator.common.Calculator;
import calculator.common.Validator;
import calculator.model.DefaultSeparator;
import calculator.model.NumberSeparator;
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

        addPositiveNumbersBySeparator(separator);

        inputView.close();
    }

    private void addPositiveNumbersBySeparator(NumberSeparator separator) {
        List<Integer> splitNumbers = separator.splitNumbers(inputView.readPlusString());
        splitNumbers.forEach(Validator::validatePositive);
        int result = Calculator.addNumbers(splitNumbers);
        outputView.printlnResult(result);
    }
}
