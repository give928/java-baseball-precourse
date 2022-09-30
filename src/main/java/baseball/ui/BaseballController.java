package baseball.ui;

import baseball.domain.Baseball;
import baseball.domain.NumberGenerator;
import baseball.domain.RandomNumberGenerator;
import baseball.domain.Result;

public class BaseballController {
    private static final String INVALID_RESTART_MESSAGE = String.format("[%s, %s]만 입력할 수 있습니다.", InputView.RESTART, InputView.EXIT);

    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public void start() {
        Baseball baseball = Baseball.from(numberGenerator);
        boolean play = true;
        while (play) {
            Result result = play(baseball);
            play = result.isNotOut() || restart(baseball);
        }
    }

    private static Result play(Baseball baseball) {
        Result result = baseball.judge(InputView.inputNumbers());
        OutputView.showResult(result);
        return result;
    }

    private static boolean restart(Baseball baseball) {
        String input = InputView.inputRestart();
        if (InputView.RESTART.equals(input)) {
            baseball.initialize();
            return true;
        }
        if (InputView.EXIT.equals(input)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_RESTART_MESSAGE);
    }
}
