package calculator;

import java.util.Arrays;
import java.util.List;

public enum DefaultSeparator {
    COMMA(","),
    COLON(":"),
    ;

    private final String label;

    DefaultSeparator(String label) {
        this.label = label;
    }

    public static List<String> getDefaultSeparators() {
        return Arrays.stream(DefaultSeparator.values())
                .map(DefaultSeparator::getLabel)
                .toList();
    }

    public String getLabel() {
        return label;
    }
}
