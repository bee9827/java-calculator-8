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
}