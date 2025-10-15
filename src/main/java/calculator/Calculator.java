package calculator;

import java.util.List;

public class Calculator {
    private Calculator() {
    }

    public static int addNumbers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static int addStringNumbers(List<String> numbers) {
        return addNumbers(toIntegerNumbers(numbers));
    }

    private static List<Integer> toIntegerNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .toList();
    }
}
