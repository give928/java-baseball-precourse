package baseball.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼 위치 관련 기능")
class PositionTest {
    @DisplayName("볼 위치 객체를 생성한다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @ValueSource(ints = {1, 3})
    void create(int value) {
        // when
        Position position = Position.from(value);

        // then
        assertThat(position).isEqualTo(Position.from(value));
    }

    @DisplayName("유효하지 않은 위치는 생성할 수 없다.")
    @ParameterizedTest(name = "{argumentsWithNames}")
    @ValueSource(ints = {0, 4})
    void invalidPosition(int value) {
        // when
        ThrowingCallable throwingCallable = () -> Position.from(value);

        // then
        assertThatThrownBy(throwingCallable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Position.INVALID_VALUE_MESSAGE);
    }
}
