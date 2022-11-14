package baseball.domain;

import java.util.Objects;

public class Number {
    static final int MIN = 1;
    static final int MAX = 9;
    static final String INVALID_VALUE_MESSAGE = String.format("볼 숫자는 %d ~ %d 사이로 입력해주세요.", MIN, MAX);

    private final int value;

    private Number(int value) {
        this.value = value;
    }

    public static Number from(int value) {
        validate(value);
        return NumberCache.cache[value + (-MIN)];
    }

    private static void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException(INVALID_VALUE_MESSAGE);
        }
    }

    private static class NumberCache {
        static final Number[] cache;

        static {
            cache = new Number[(MAX - MIN) + 1];
            int j = MIN;
            for(int i = 0; i < cache.length; i++) {
                cache[i] = new Number(j++);
            }
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
