package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.common.Calculator;
import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void add() {
        List<Integer> numbers = List.of(1,2,3);

        assertThat(Calculator.addNumbers(numbers)).isEqualTo(6);
    }
}