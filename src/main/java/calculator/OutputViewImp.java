package calculator;

public class OutputViewImp implements OutputView {
    @Override
    public void printlnResult(int result) {
        System.out.printf("결과 : %d%n", result);
    }
}
