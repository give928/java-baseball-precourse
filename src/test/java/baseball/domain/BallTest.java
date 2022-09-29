package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BallTest {
    private static Stream<Arguments> judgeArguments() {
        return Stream.of(Arguments.of(Ball.of(1, 2), -1),
                         Arguments.of(Ball.of(2, 1), 0),
                         Arguments.of(Ball.of(1, 1), 1));
    }

    @DisplayName("볼 객체를 생성한다.")
    @Test
    void create() {
        // given
        int position = 1;
        int number = 1;

        // when
        Ball ball = Ball.of(position, number);

        // then
        assertThat(ball).isEqualTo(Ball.of(position, number));
    }

    @DisplayName("볼을 판정한다.")
    @ParameterizedTest(name = "{displayName}({argumentsWithNames})")
    @MethodSource("judgeArguments")
    void judge(Ball userBall, int expected) {
        // given
        Ball computerBall = Ball.of(1, 1);

        // when
        int actual = computerBall.judge(userBall);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}