package calculator;

import calculator.view.InputView;
import calculator.view.InputViewImp;
import calculator.view.OutputView;
import calculator.view.OutputViewImp;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputViewImp();
        OutputView outputView = new OutputViewImp();

        Controller controller = new Controller(inputView,outputView);
        controller.run();
    }
}
