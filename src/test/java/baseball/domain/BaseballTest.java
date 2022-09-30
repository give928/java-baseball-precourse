package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 관련 기능")
class BaseballTest {
    private static final List<Integer> NUMBERS = Arrays.asList(1, 2, 3);
    private static final String JUDGEMENT_NUMBERS = "123";

    @DisplayName("컴퓨터 임의의 수를 생성한다.")
    @Test
    void create() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        // when
        Baseball baseball = Baseball.from(numberGenerator);

        // then
        assertThat(baseball).extracting("balls")
                .isNotNull();
    }

    @DisplayName("숫자 3개를 입력 받아서 결과를 판정한다.")
    @Test
    void judge() {
        // given
        Baseball baseball = Baseball.from(() -> NUMBERS);

        // when
        Result result = baseball.judge(JUDGEMENT_NUMBERS);

        // then
        assertThat(result.isOut()).isTrue();
    }

    @DisplayName("컴퓨터 임의의 수를 다시 생성한다.")
    @Test
    void generate() {
        // given
        Queue<List<Integer>> queue = new LinkedList<>(Arrays.asList(NUMBERS, Arrays.asList(4, 5, 6)));
        Baseball baseball = Baseball.from(queue::poll);

        // when
        Result result123 = baseball.judge(JUDGEMENT_NUMBERS);

        // then
        assertThat(result123.isOut()).isTrue();

        // when
        baseball.initialize();
        Result result456 = baseball.judge(JUDGEMENT_NUMBERS);

        // then
        assertThat(result456.isOut()).isFalse();
    }
}
