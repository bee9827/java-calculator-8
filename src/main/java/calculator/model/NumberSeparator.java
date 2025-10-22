package calculator.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberSeparator {
    public static final Pattern SEPARATOR_PATTERN = Pattern.compile("//(.+)\\\\n(.+)");
    public static final String NUMBER_REGEX = "\\d+";
    public static final int SEPARATOR_SIZE = 1;

    private static final int CUSTOM_SEPARATOR_LOCATION = 1;
    private static final int NUMBERS_LOCATION = 2;

    private final Set<String> separator = new HashSet<>();

    public NumberSeparator(List<String> separator) {
        separator.forEach(this::addSeparator);
    }

    public static List<String> extractCustomSeparators(String combined) {
        Matcher matcher = SEPARATOR_PATTERN.matcher(combined);
        if (matcher.matches()) {
            return Arrays.stream(matcher.group(CUSTOM_SEPARATOR_LOCATION).split(""))
                    .toList();
        }
        return Collections.emptyList();
    }

    public static String extractNumbersWithSeparators(String combined) {
        Matcher matcher = SEPARATOR_PATTERN.matcher(combined);
        if (matcher.matches()) {
            return matcher.group(NUMBERS_LOCATION);
        }
        return combined;
    }

    public void addSeparator(String customSeparator) {
        validateSeparator(customSeparator);
        separator.add(customSeparator);
    }

    public List<Integer> splitNumbers(String numbersWithSeparators) {
        String numbers = extractNumbersWithSeparators(numbersWithSeparators);
        String separatorRegex = getSeparatorRegex();

        List<String> numberList = Arrays.asList(numbers.split(separatorRegex));

        return numberList.stream()
                .map(Integer::parseInt)
                .toList();
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
            throw new IllegalArgumentException("유효하지 않은 커스텀 구분자 : %s".formatted(customSeparator));
        }
    }

    private void validateNumber(String customSeparator) {
        if (customSeparator.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("유효하지 않은 커스텀 구분자 : %s".formatted(customSeparator));
        }
    }

    private void validateDuplicate(String customSeparator) {
        if (separator.contains(customSeparator)) {
            throw new IllegalArgumentException("유효하지 않은 커스텀 구분자 : %s".formatted(customSeparator));
        }
    }

    private String getSeparatorRegex() {
        String regex = String.join("", separator);
        return "[%s]".formatted(Pattern.quote(regex));
    }
}
