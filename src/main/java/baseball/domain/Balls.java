package baseball.domain;

import java.util.*;

public class Balls {
    static final String INVALID_VALUES_MESSAGE = String.format("중복되지 않는 숫자 %d개를 입력해주세요.", Position.MAX);

    private final List<Ball> values;

    private Balls(List<Ball> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public static Balls from(List<Integer> numbers) {
        return new Balls(mapBalls(numbers));
    }

    public static Balls from(String text) {
        return from(convertToNumbers(text));
    }

    private static List<Integer> convertToNumbers(String text) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            numbers.add(Character.getNumericValue(text.charAt(i)));
        }
        return numbers;
    }

    private static void validate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != Position.MAX) {
            throw new IllegalArgumentException(INVALID_VALUES_MESSAGE);
        }
    }

    private static List<Ball> mapBalls(List<Integer> numbers) {
        validate(numbers);
        List<Ball> values = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            values.add(Ball.of(i + 1, numbers.get(i)));
        }
        return values;
    }

    public List<Judgement> judge(Balls balls) {
        List<Judgement> judgements = new ArrayList<>();
        balls.values.forEach(ball -> judgements.add(judge(ball)));
        return Collections.unmodifiableList(judgements);
    }

    private Judgement judge(Ball ball) {
        List<Judgement> judgements = new ArrayList<>();
        values.forEach(value -> judgements.add(value.judge(ball)));
        return judge(judgements);
    }

    private Judgement judge(List<Judgement> judgements) {
        if (judgements.contains(Judgement.STRIKE)) {
            return Judgement.STRIKE;
        }
        if (judgements.contains(Judgement.BALL)) {
            return Judgement.BALL;
        }
        return Judgement.NOTHING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Balls balls = (Balls) o;
        return Objects.equals(values, balls.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
