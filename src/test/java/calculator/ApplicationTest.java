package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("\\구분자 사용")
    void backSlashSeparator(){
        assertSimpleTest(() -> {
            run("//\\\\n1:2\\3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("정규식 예약어 구분자")
    void regexSeparator(){
        assertSimpleTest(() -> {
            run("//[\\n1:2[3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 중복_커스텀_구분자_예외(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//:\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 다중_커스텀_구분자_사용(){
        assertSimpleTest(() -> {
            run("//[]';\\n1[2]3;4'5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
