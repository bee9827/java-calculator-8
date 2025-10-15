package calculator;

import java.util.Arrays;
import java.util.List;

public class Separator {
    private final List<String> separator;

    public Separator(List<String> separator) {
        this.separator = separator;
    }

    public List<String> split(String combinedStr) {
        return Arrays.stream(combinedStr.split(getSeparatorRegex()))
                .toList();
    }

    private String getSeparatorRegex() {
        String regex = String.join("", separator);
        return "[%s]".formatted(regex);
    }
}
