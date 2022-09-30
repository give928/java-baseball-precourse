package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼 관련 기능")
class BallTest {
    private static final int POSITION = 1;
    private static final int NUMBER = 1;

    private static Stream<Arguments> judgeArguments() {
        return Stream.of(Arguments.of(Ball.of(1, 2), Judgement.NOTHING),
                         Arguments.of(Ball.of(2, 1), Judgement.BALL),
                         Arguments.of(Ball.of(1, 1), Judgement.STRIKE));
    }

    @DisplayName("볼 객체를 생성한다.")
    @Test
    void create() {
        // when
        Ball ball = Ball.of(POSITION, NUMBER);

        // then
        assertThat(ball).isEqualTo(Ball.of(POSITION, NUMBER));
    }

    @DisplayName("볼을 판정한다.")
    @ParameterizedTest(name = "{1}")
    @MethodSource("judgeArguments")
    void judge(Ball userBall, Judgement expected) {
        // given
        Ball computerBall = Ball.of(POSITION, NUMBER);

        // when
        Judgement actual = computerBall.judge(userBall);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
