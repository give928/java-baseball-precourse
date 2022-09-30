package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 게임_후_종료() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("789", "123", "124", "624", "234", "264", "246", "2");
                    assertThat(output()).isEqualTo("숫자를 입력해주세요 : 낫싱\n" +
                                                           "숫자를 입력해주세요 : 1볼\n" +
                                                           "숫자를 입력해주세요 : 2볼\n" +
                                                           "숫자를 입력해주세요 : 3볼\n" +
                                                           "숫자를 입력해주세요 : 1볼 1스트라이크\n" +
                                                           "숫자를 입력해주세요 : 2볼 1스트라이크\n" +
                                                           "숫자를 입력해주세요 : 3스트라이크\n" +
                                                           "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n" +
                                                           "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                },
                2, 4, 6
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "3"})
    void 게임종료_후_재시작_입력_예외(String restart) {
        assertRandomNumberInRangeTest(
                () -> assertThatThrownBy(() -> run("135", restart))
                        .isInstanceOf(IllegalArgumentException.class),
                1, 3, 5
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234", "12", "122", "012", "1 3", "12a"})
    void 예외_테스트(String number) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(number))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
