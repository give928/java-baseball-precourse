package baseball.ui;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private static final String NUMBERS_MESSAGE = "숫자를 입력해주세요 : ";
    static final String RESTART = "1";
    static final String EXIT = "2";
    private static final String RESTART_MESSAGE = String.format("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.", RESTART, EXIT);

    private InputView() {
    }

    public static String inputNumbers() {
        write(NUMBERS_MESSAGE);
        return read();
    }

    public static String inputRestart() {
        write(RESTART_MESSAGE);
        return read();
    }

    private static void write(String message) {
        System.out.println(message);
    }

    public static String read() {
        return Console.readLine();
    }
}
