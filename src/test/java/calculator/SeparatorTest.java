package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SeparatorTest {

    @Test
    void split() {
        Separator separator = new Separator(DefaultSeparator.getDefaultSeparators());

        List<String> split = separator.split("1:23");

        assertThat(split).containsExactly("1", "2", "3");
    }

    @Test
    void addSeparator() {
        Separator separator = new Separator(DefaultSeparator.getDefaultSeparators());
        String custom = "[";
        separator.addSeparator(custom);

        assertThat(separator.getSeparator()).contains(custom);

    }

    @Test
    void extractSeparator() {
        Separator separator = new Separator(DefaultSeparator.getDefaultSeparators());
        String customSeparatorInput = "//\\\n";
        String customSeparator = separator.extractSeparator(customSeparatorInput);

        assertThat(customSeparator).contains("\\");
    }
}