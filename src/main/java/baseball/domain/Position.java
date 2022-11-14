package baseball.domain;

import java.util.Objects;

public class Position {
    private static final int MIN = 1;
    static final int MAX = 3;
    static final String INVALID_VALUE_MESSAGE = String.format("볼 위치는 %d ~ %d 사이의 숫자로 입력해주세요.", MIN, MAX);

    private final int value;

    private Position(int value) {
        this.value = value;
    }

    public static Position from(int value) {
        validate(value);
        return PositionCache.cache[value + (-MIN)];
    }

    private static void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException(INVALID_VALUE_MESSAGE);
        }
    }

    private static class PositionCache {
        static final Position[] cache;

        static {
            cache = new Position[(MAX - MIN) + 1];
            int j = MIN;
            for(int i = 0; i < cache.length; i++) {
                cache[i] = new Position(j++);
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
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
