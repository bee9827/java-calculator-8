package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SeparatorTest {

    @Test
    void split() {
        CalculatorSeparator separator = new CalculatorSeparator(DefaultSeparator.getDefaultSeparators());
        separator.addSeparator("[");

        List<Integer> split = separator.splitNumbers("1:2[3");

        assertThat(split).containsExactly(1,2,3);
    }

    @Test
    void addSeparator() {
        CalculatorSeparator separator = new CalculatorSeparator(DefaultSeparator.getDefaultSeparators());
        String custom = "[";
        separator.addSeparator(custom);

        assertThat(separator.getSeparator()).contains(custom);

    }

    @Test
    void extractSeparator() {
        CalculatorSeparator separator = new CalculatorSeparator(DefaultSeparator.getDefaultSeparators());
        String customSeparatorInput = "//[]'\\n1";
        List<String> customSeparator = separator.extractSeparators(customSeparatorInput);

        assertThat(customSeparator)
                .contains("[")
                .contains("]")
                .contains("'");
    }
}