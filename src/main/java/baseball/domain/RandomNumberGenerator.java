package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class RandomNumberGenerator implements NumberGenerator {
    public List<Integer> generate() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Position.MAX) {
            numbers.add(Randoms.pickNumberInRange(Number.MIN, Number.MAX));
        }
        return new ArrayList<>(numbers);
    }
}
