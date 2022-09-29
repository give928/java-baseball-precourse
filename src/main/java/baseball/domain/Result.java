package baseball.domain;

import java.util.List;

public class Result {
    private static final String INVALID_SIZE_MESSAGE = "숫자 3개에 대한 판정을 모두 입력해주세요.";

    private final int strike;
    private final int ball;

    private Result(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public static Result from(List<Judgement> judgements) {
        validate(judgements);
        int strike = 0;
        int ball = 0;
        for (Judgement judgement : judgements) {
            strike += getStrikeCount(judgement);
            ball += getBallCount(judgement);
        }
        return new Result(strike, ball);
    }

    private static void validate(List<Judgement> judgements) {
        if (judgements.size() != Position.MAX) {
            throw new IllegalStateException(INVALID_SIZE_MESSAGE);
        }
    }

    private static int getStrikeCount(Judgement judgement) {
        if (judgement.isStrike()) {
            return 1;
        }
        return 0;
    }

    private static int getBallCount(Judgement judgement) {
        if (judgement.isBall()) {
            return 1;
        }
        return 0;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public boolean isOut() {
        return strike == Position.MAX;
    }
}
