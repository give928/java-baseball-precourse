package baseball.domain;

import java.util.Objects;

public class Ball {
    private final Position position;
    private final int number;

    private Ball(Position position, int number) {
        this.position = position;
        this.number = number;
    }

    public static Ball of(int position, int number) {
        return new Ball(Position.from(position), number);
    }

    public Judgement judge(Ball ball) {
        if (this.equals(ball)) {
            return Judgement.STRIKE;
        }
        if (number == ball.number) {
            return Judgement.BALL;
        }
        return Judgement.NOTING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball = (Ball) o;
        return number == ball.number && Objects.equals(position, ball.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number);
    }
}
