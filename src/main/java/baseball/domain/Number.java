package baseball.domain;

import java.util.Objects;

public class Number {
    static final int MIN = 1;
    static final int MAX = 9;
    static final String INVALID_VALUE_MESSAGE = "볼 숫자는 1 ~ 9 사이로 입력해주세요.";

    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public static Number from(int value) {
        validate(value);
        return new Number(value);
    }

    private static void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException(INVALID_VALUE_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
