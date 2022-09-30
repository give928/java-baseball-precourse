package baseball.domain;

import java.util.*;

public class Balls {
    static final String INVALID_VALUES_MESSAGE = "중복되지 않는 숫자 3개를 입력해주세요.";

    private final List<Ball> values;

    public Balls(List<Ball> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public static Balls from(List<Integer> numbers) {
        return new Balls(mapBalls(numbers));
    }

    public static Balls from(String text) {
        return new Balls(mapBalls(convertToNumbers(text)));
    }

    private static List<Integer> convertToNumbers(String text) {
        try {
            List<Integer> numbers = new ArrayList<>();
            addNumbers(numbers, text);
            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VALUES_MESSAGE);
        }
    }

    private static void addNumbers(List<Integer> numbers, String text) {
        for (int i = 0; i < text.length(); i++) {
            numbers.add(Character.getNumericValue(text.charAt(i)));
        }
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
        return findJudgement(judgements);
    }

    private Judgement findJudgement(List<Judgement> judgements) {
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
