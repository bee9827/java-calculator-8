package calculator;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputViewImp();
        OutputView outputView = new OutputViewImp();

        Controller controller = new Controller(inputView,outputView);
        controller.run();
    }
}
