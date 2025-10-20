package calculator.common;

public class Validator {
    public static void validatePositive(Integer number){
        if(number < 0){
            throw new IllegalArgumentException("숫자는 음수일 수 없습니다.");
        }
    }
}
