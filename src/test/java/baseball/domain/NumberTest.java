package baseball.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberTest {
    @DisplayName("볼 숫자 객체를 생성한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @ValueSource(ints = {1, 9})
    void create(int value) {
        // when
        Number number = Number.from(value);

        // then
        assertThat(number).isEqualTo(Number.from(value));
    }

    @DisplayName("유효하지 않은 숫자는 생성할 수 없다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @ValueSource(ints = {0, 10})
    void invalidNumber(int value) {
        // when
        ThrowingCallable throwingCallable = () -> Number.from(value);

        // then
        assertThatThrownBy(throwingCallable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Number.INVALID_VALUE_MESSAGE);
    }
}
