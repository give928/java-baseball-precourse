package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public final class RandomNumberGenerator implements NumberGenerator {
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < Position.MAX) {
            addNumber(numbers);
        }
        return numbers;
    }

    private static void addNumber(List<Integer> numbers) {
        int number = Randoms.pickNumberInRange(Number.MIN, Number.MAX);
        if (!numbers.contains(number)) {
            numbers.add(number);
        }
    }
}
