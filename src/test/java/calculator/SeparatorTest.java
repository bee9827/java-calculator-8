package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.model.DefaultSeparator;
import calculator.model.NumberSeparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class SeparatorTest {

    @Test
    void split() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());
        separator.addSeparator("[");

        List<Integer> split = separator.splitNumbers("1:2[3");

        assertThat(split).containsExactly(1,2,3);
    }

    @Test
    void addSeparator() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());
        String custom = "[";
        separator.addSeparator(custom);

        assertThat(separator.getSeparator()).contains(custom);

    }

    @Test
    void extractSeparator() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());
        String customSeparatorInput = "//[]'\\n1";
        List<String> customSeparator = separator.extractSeparators(customSeparatorInput);

        System.out.println(customSeparator);
        assertThat(customSeparator)
                .contains("[")
                .contains("]")
                .contains("'");
    }
}