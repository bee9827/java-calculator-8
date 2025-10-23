package calculator.view;

public class ConsoleOutputView implements OutputView {
    @Override
    public void printlnSum(int result) {
        System.out.printf("결과 : %d%n", result);
    }
}
