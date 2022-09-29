package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballTest {
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
        Baseball baseball = Baseball.from(() -> Arrays.asList(1, 2, 3));

        // when
        Result result = baseball.judge("123");

        // then
        assertThat(result.isOut()).isTrue();
    }

    @DisplayName("컴퓨터 임의의 수를 다시 생성한다.")
    @Test
    void generate() {
        // given
        Queue<List<Integer>> queue = new LinkedList<>(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));
        Baseball baseball = Baseball.from(queue::poll);

        // when
        Result result123 = baseball.judge("123");

        // then
        assertThat(result123.isOut()).isTrue();

        // when
        baseball.initialize();
        Result result456 = baseball.judge("123");

        // then
        assertThat(result456.isOut()).isFalse();
    }
}
