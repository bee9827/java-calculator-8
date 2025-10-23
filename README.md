# java-calculator-precourse

입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.
이 저장소는 리팩토링을 통해 가독성, 책임 분리, 컨트롤러 흐름 개선을 중점적으로 다듬은 버전입니다.

| 브랜치                                                                    | 설명                   |
|------------------------------------------------------------------------|----------------------|
| [`bee9827`](https://github.com/bee9827/java-calculator-8/tree/bee9827) | 원본 코드 (리팩토링 전)       |
| [`review`](https://github.com/bee9827/java-calculator-8/tree/review)   | 리팩토링 후 코드 리뷰 요청용 브랜치 |

## 구현할 기능 목록

```
//{커스텀 구분자}\n{숫자와 구분자}
//;\n1;2;3
```

-[x] {커스텀 구분자} 분리 `//;\n1;2;3` -> `;`
-[x] {숫자와 구분자} 분리 `//;\n1;2;3` -> `1;2;3`

- [x] 커스텀 구분자를 추가한다.
    - [x] 중복된 구분자를 추가할 경우 예외를 발생시킨다.
    - [x] 구분자가 1글자가 아니면 예외를 발생시킨다.
    - [x] 구분자가 숫자라면 예외를 발생시킨다.

- [x] 숫자 분리
    - 쉼표(","), 콜론(":"), 추가된 커스텀 구분자 기준으로 숫자를 분리한다.
    - 문자를 숫자로 변환한다 `1;2;3` ->`[1,2,3]`  
      `/;\n1;2;3` 과 같이 입력 형식이 맞지 않을 경우 숫자로 변환하면서 예외가 발생한다.

- [x] 양수인 숫자를 관리한다.
    - 숫자가 양수가 아니라면 예외를 발생시킨다.
    - 숫자가 없다면 예외를 발생시킨다. (공백이 입력 되면 여기서 예외 발생)
- [x] 분리된 숫자를 더한다. `[1,2,3]` -> `6`

사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.

## 입출력 요구 사항

### 입력

- 구분자와 양수로 구성된 문자열

### 출력

- 덧셈 결과

```
결과 : 6
```

### 실행 결과 예시

```
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```

## 고민한 것들

### 가독성

코드 리뷰를 진행 하면서 복잡한 부분은 상대방도 리뷰하기가 힘들다고 느꼈습니다.
어떤 목적을 가지고 설계를 했는지, 어떤 행위를 하는 메서드인지 보다 명확하게 나타내기 위해서 최대한 읽기 쉽도록 코딩하였습니다.
혹시나 읽히지 않거나 흐름이 복잡하다고 느껴진다면 리뷰 부탁드립니다.

### 객체

객체의 책임에 집중했습니다. 값을 지니고 있는 객체가 검증하고, 그 객체를 통하여 값을 변환하고 가공할 수 있도록 리팩토링 하였습니다.
그 결과 값을 토스하는 것이 아닌 객체를 만들고 객체의 메소드를 사용하게 되었습니다. 그리고 Controller에서 흐름을 더 잘 볼수 있게 된것 같습니다.
객체지향 패러다임에 반하는 코드가 있다면 알려주시면 감사하겠습니다
#### 변경전

```java
public class Controller {
    public void run() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());
        addPositiveNumbersBySeparator(separator);
        inputView.close();
    }

    private void addPositiveNumbersBySeparator(NumberSeparator separator) {
        List<Integer> splitNumbers = separator.splitNumbers(inputView.readPlusString());
        splitNumbers.forEach(Validator::validatePositive);
        int result = Calculator.addNumbers(splitNumbers);
        outputView.printlnResult(result);
    }
}
```

#### 리팩토링 후

```java
public class Controller {
    ...

    public void run() {
        NumberSeparator separator = new NumberSeparator(DefaultSeparator.getDefaultSeparators());

        PositiveNumberCalculator calculator = createCalculator(inputView.readCombinedNumbers(), separator);
        Integer sum = calculator.sum();
        outputView.printlnSum(sum);

        inputView.close();
    }

    private PositiveNumberCalculator createCalculator(String combinedNumbers, NumberSeparator separator) {
        List<String> customSeparator = NumberSeparator.extractCustomSeparators(combinedNumbers);
        String numbersWithSeparator = NumberSeparator.extractNumbersWithSeparators(combinedNumbers);

        customSeparator.forEach(separator::addSeparator);
        List<Integer> splitNumbers = separator.splitNumbers(numbersWithSeparator);

        return new PositiveNumberCalculator(splitNumbers);
    }
}
```

### 예외처리

객체의 책임에 집중하게 되면서 예외처리가 이전보다 쉬워진것을 느꼈습니다.
하지만 여전히 남아있는 문제들이 있습니다.
예를 들면 String에서 Integer로 바꾸는 함수에서 `IllegalArgumentException`을 상속한 `NumberFormatException` 발생하게 됩니다.
저는 이 부분이 애매해서 처리하지 못했습니다.
또한 예외 문구를 너무 자세하게 알려주면 안된다는 말이 기억나 문구를 어떻게 처리해야 할지 모르겠습니다.

- 예외처리 문구를 어떻게 하셨나요 -> 자세한 예외 데이터를 넣어 주셨나요? 아니면 각 예외마다 다르게 문구를 쓰셨나요?
- 예외처리 문구를 한곳에서 관리한다면 데이터 변수값은 어떻게 하셨나요?
- InputView 에서 입력 형식과 다르다면 예외를 던지는 것은 잘못된 것 일까요? (ex 숫자를 입력 받아야 할때 : 문자를 숫자로 변환 할때 생기는 예외), 그렇다면 그 이유가 무엇인가요?
