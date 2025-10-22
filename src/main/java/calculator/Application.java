package calculator;

import calculator.view.InputView;
import calculator.view.ConsoleInputView;
import calculator.view.OutputView;
import calculator.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        Controller controller = new Controller(inputView,outputView);
        controller.run();
    }
}
