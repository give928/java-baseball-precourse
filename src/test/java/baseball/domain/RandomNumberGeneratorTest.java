package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("임의의 수 생성 관련 기능")
class RandomNumberGeneratorTest {
    @DisplayName("1 ~ 9 사이의 서로 다른 임의의 수 3개를 생성한다.")
    @Test
    void generate() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        // when
        List<Integer> numbers = numberGenerator.generate();

        // then
        assertThat(numbers).allMatch(number -> Number.MIN <= number && number <= Number.MAX)
                .doesNotHaveDuplicates()
                .hasSize(Position.MAX);
    }
}
