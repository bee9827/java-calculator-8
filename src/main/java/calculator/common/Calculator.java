package calculator.common;

import java.util.List;

public class Calculator {
    private Calculator() {
    }

    public static int addNumbers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }
}
