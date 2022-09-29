package baseball.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BallsTest {
    private static final String NUMBERS = "425";

    private static Stream<Arguments> judgeArguments() {
        return Stream.of(Arguments.of("789", new Judgement[]{Judgement.NOTHING, Judgement.NOTHING, Judgement.NOTHING}),
                         Arguments.of("123", new Judgement[]{Judgement.NOTHING, Judgement.STRIKE, Judgement.NOTHING}),
                         Arguments.of("456", new Judgement[]{Judgement.STRIKE, Judgement.BALL, Judgement.NOTHING}),
                         Arguments.of(NUMBERS, new Judgement[]{Judgement.STRIKE, Judgement.STRIKE, Judgement.STRIKE}));
    }

    @DisplayName("볼 컬렉션 객체를 생성한다.")
    @Test
    void create() {
        // when
        Balls balls = Balls.from(NUMBERS);

        // then
        assertThat(balls).isEqualTo(Balls.from(NUMBERS));
    }

    @DisplayName("3개의 숫자가 아니면 볼 컬렉션 객체를 생성할 수 없다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @ValueSource(strings = {"42", "4259"})
    void invalidNumberSize(String text) {
        // when
        ThrowingCallable throwingCallable = () -> Balls.from(text);

        // then
        assertThatThrownBy(throwingCallable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.INVALID_VALUES_MESSAGE);
    }

    @DisplayName("볼 3개의 판정을 구한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @MethodSource("judgeArguments")
    void judge(String numbers, Judgement[] expected) {
        // given
        Balls balls = Balls.from(NUMBERS);

        // when
        List<Judgement> actual = balls.judge(Balls.from(numbers));

        // then
        assertThat(actual).containsExactly(expected);
    }
}
