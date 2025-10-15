package calculator;

import java.util.List;

public class Calculator {
    private Calculator() {
    }

    public static int addPositiveNumbers(List<Integer> numbers) {
        numbers.forEach(Calculator::validatePositive);
        return numbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("양수를 입력해 주세요");
        }
    }

    public static int addPositiveStringNumbers(List<String> numbers) {
        return addPositiveNumbers(toIntegerNumbers(numbers));
    }

    private static List<Integer> toIntegerNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .toList();
    }
}
