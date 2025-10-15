package calculator;

import camp.nextstep.edu.missionutils.Console;

public class InputViewImp implements InputView {
    public String readPlusString(){
        System.out.println("덧셈할 문자열을 입력해 주세요");
        return Console.readLine();
    }

    public void close(){
        Console.close();
    }
}
