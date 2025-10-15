package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void add() {
        List<String> numbers = List.of("1", "2", "3");

        assertThat(Calculator.addPositiveStringNumbers(numbers)).isEqualTo(6);
    }
}