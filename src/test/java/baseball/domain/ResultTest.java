package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("결과 관련 기능")
class ResultTest {
    public static Stream<Arguments> resultArguments() {
        return Stream.of(Arguments.of(Arrays.asList(Judgement.STRIKE, Judgement.STRIKE, Judgement.STRIKE), 3, 0),
                         Arguments.of(Arrays.asList(Judgement.STRIKE, Judgement.BALL, Judgement.NOTHING), 1, 1),
                         Arguments.of(Arrays.asList(Judgement.BALL, Judgement.BALL, Judgement.BALL), 0, 3),
                         Arguments.of(Arrays.asList(Judgement.NOTHING, Judgement.NOTHING, Judgement.NOTHING), 0, 0));
    }

    public static Stream<Arguments> outArguments() {
        return Stream.of(Arguments.of(Arrays.asList(Judgement.STRIKE, Judgement.STRIKE, Judgement.STRIKE), true),
                         Arguments.of(Arrays.asList(Judgement.STRIKE, Judgement.STRIKE, Judgement.NOTHING), false));
    }

    public static Stream<Arguments> atArguments() {
        return Stream.of(Arguments.of(Arrays.asList(Judgement.BALL, Judgement.STRIKE, Judgement.NOTHING), false, true, true),
                         Arguments.of(Arrays.asList(Judgement.NOTHING, Judgement.NOTHING, Judgement.NOTHING), true, false, false));
    }

    @DisplayName("결과 객체를 생성하고 스트라이크, 볼을 확인한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @MethodSource("resultArguments")
    void create(List<Judgement> judgements, int strike, int ball) {
        // when
        Result result = Result.from(judgements);

        // then
        assertThat(result.getStrike()).isEqualTo(strike);
        assertThat(result.getBall()).isEqualTo(ball);
    }

    @DisplayName("아웃(3스트라이크) 여부를 확인한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @MethodSource("outArguments")
    void out(List<Judgement> judgements, boolean out) {
        // when
        Result result = Result.from(judgements);

        // then
        assertThat(result.isOut()).isEqualTo(out);
    }

    @DisplayName("아웃이 아닌지 확인한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @MethodSource("outArguments")
    void notOut(List<Judgement> judgements, boolean out) {
        // when
        Result result = Result.from(judgements);

        // then
        assertThat(result.isNotOut()).isEqualTo(!out);
    }

    @DisplayName("낫싱, 스트라이크, 볼 여부를 확인한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @MethodSource("atArguments")
    void at(List<Judgement> judgements, boolean nothing, boolean strike, boolean ball) {
        // when
        Result result = Result.from(judgements);

        // then
        assertThat(result.isNothing()).isEqualTo(nothing);
        assertThat(result.hasStrike()).isEqualTo(strike);
        assertThat(result.hasBall()).isEqualTo(ball);
    }
}
