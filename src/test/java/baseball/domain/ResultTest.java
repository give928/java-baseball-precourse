package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("결과 객체를 생성한다.")
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
}
