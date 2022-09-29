package baseball.domain;

public class Baseball {
    private final NumberGenerator numberGenerator;
    private Balls balls;

    private Baseball(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        initialize();
    }

    public static Baseball from(NumberGenerator numberGenerator) {
        return new Baseball(numberGenerator);
    }

    public void initialize() {
        this.balls = Balls.from(numberGenerator.generate());
    }

    public Result judge(String text) {
        return Result.from(balls.judge(Balls.from(text)));
    }
}
