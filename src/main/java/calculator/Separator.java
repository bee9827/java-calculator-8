package calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    public static final Pattern SEPARATOR_PATTERN = Pattern.compile("^//(.)\\n$");
    public static final String NUMBER_REGEX = "\\d+";
    public static final int SEPARATOR_SIZE = 1;
    private final Set<String> separator = new HashSet<>();

    public Separator(List<String> separator) {
        separator.forEach(this::addSeparator);
    }

    public String extractSeparator(String combinedSeparator) {
        Matcher matcher = SEPARATOR_PATTERN.matcher(combinedSeparator);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid separator: %s".formatted(combinedSeparator));
    }

    public List<String> split(String combinedStr) {
        if (combinedStr.charAt(0) == '/') {
            String customSeparator = extractSeparator(combinedStr);
            addSeparator(customSeparator);
        }

        return Arrays.stream(combinedStr.split(getSeparatorRegex()))
                .toList();
    }

    public void addSeparator(String customSeparator) {
        validateSeparator(customSeparator);
        separator.add(customSeparator);
    }

    public Set<String> getSeparator() {
        return Collections.unmodifiableSet(separator);
    }

    private void validateSeparator(String customSeparator) {
        validateDuplicate(customSeparator);
        validateNumber(customSeparator);
        validateSize(customSeparator);
    }

    private void validateSize(String customSeparator) {
        if (customSeparator == null || customSeparator.length() != SEPARATOR_SIZE) {
            throw new IllegalArgumentException("Invalid custom separator: " + customSeparator);
        }
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
