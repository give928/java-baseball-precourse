package baseball.domain;

import java.util.Objects;

public class Ball {
    private final Position position;
    private final Number number;

    private Ball(Position position, Number number) {
        this.position = position;
        this.number = number;
    }

    public static Ball of(int position, int number) {
        return new Ball(Position.from(position), Number.from(number));
    }

    public Judgement judge(Ball ball) {
        if (this.equals(ball)) {
            return Judgement.STRIKE;
        }
        if (number.equals(ball.number)) {
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
        Ball ball = (Ball) o;
        return Objects.equals(position, ball.position) && Objects.equals(number, ball.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number);
    }
}
