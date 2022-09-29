package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {
    @DisplayName("1 ~ 9 사이의 서로 다른 임의의 수 3개를 생성한다.")
    @Test
    void generate() {
        // when
        List<Integer> numbers = RandomNumberGenerator.generate();

        // then
        assertThat(numbers).allMatch(number -> Number.MIN <= number && number <= Number.MAX)
                .doesNotHaveDuplicates()
                .hasSize(Position.MAX);
    }
}