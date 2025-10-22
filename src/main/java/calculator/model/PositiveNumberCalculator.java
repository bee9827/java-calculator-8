package calculator.model;

import java.util.List;

public class PositiveNumberCalculator {
    private static final int POSITIVE_THRESHOLD = 1;

    private final List<Integer> numbers;

    public PositiveNumberCalculator(List<Integer> numbers) {
        numbers.forEach(this::validatePositiveNumber);
        validateNonEmpty(numbers);
        this.numbers = numbers;
    }

    public Integer sum() {
        try {
            return numbers.stream()
                    .reduce(0, Math::addExact);
        }catch (ArithmeticException e) {
            throw new IllegalArgumentException("숫자가 너무 커 합을 구할 수 없습니다.");
        }
    }

    private void validateNonEmpty(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("양수인 숫자를 입력해 주세요");
        }
    }

    private void validatePositiveNumber(Integer number) {
        if (number < POSITIVE_THRESHOLD) {
            throw new IllegalArgumentException("양수인 숫자를 입력해 주세요");
        }
    }

}
