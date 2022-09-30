package baseball.ui;

import baseball.domain.Result;

public final class OutputView {
    private static final String BALL_MESSAGE = "%d볼 ";
    private static final String STRIKE_MESSAGE = "%d스트라이크";
    private static final String NOTHING_MESSAGE = "낫싱";
    private static final String OUT_MESSAGE = "%d개의 숫자를 모두 맞히셨습니다! 게임 종료";

    private OutputView() {
    }

    public static void showResult(Result result) {
        write(getResultMessage(result));
        if (result.isOut()) {
            write(String.format(OUT_MESSAGE, result.getStrike()));
        }
    }

    private static String getResultMessage(Result result) {
        if (result.isNothing()) {
            return NOTHING_MESSAGE;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasBall()) {
            stringBuilder.append(String.format(BALL_MESSAGE, result.getBall()));
        }
        if (result.hasStrike()) {
            stringBuilder.append(String.format(STRIKE_MESSAGE, result.getStrike()));
        }
        return stringBuilder.toString().trim();
    }

    public static void write(String message) {
        System.out.println(message);
    }
}
