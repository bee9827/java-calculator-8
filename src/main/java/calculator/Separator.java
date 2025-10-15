package calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Separator {
    public static final String NUMBER_REGEX = "\\d+";
    private final Set<String> separator = new HashSet<>();

    public Separator(List<String> separator) {
        separator.forEach(this::addSeparator);
    }

    public List<String> split(String combinedStr) {
        return Arrays.stream(combinedStr.split(getSeparatorRegex()))
                .toList();
    }

    public void addSeparator(String customSeparator) {
        validateDuplicate(customSeparator);
        validateNumber(customSeparator);

        separator.add(customSeparator);
    }

    public Set<String> getSeparator() {
        return Collections.unmodifiableSet(separator);
    }

    private void validateNumber(String customSeparator) {
        if (customSeparator.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("Invalid custom separator: " + customSeparator);
        }
    }

    private void validateDuplicate(String customSeparator) {
        if (separator.contains(customSeparator)) {
            throw new IllegalArgumentException("Duplicate separator found");
        }
    }

    private String getSeparatorRegex() {
        String regex = String.join("", separator);
        return "[%s]".formatted(regex);
    }
}
