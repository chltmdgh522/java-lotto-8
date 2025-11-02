package lotto.domain.entity.lotto;

import java.util.HashSet;
import java.util.List;
import lotto.global.error.ErrorCode;
import lotto.global.error.LottoException;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    // 번호 개수 검증
    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoException(ErrorCode.INVALID_WINNING_NUMBERS_COUNT);
        }
    }

    // 번호 범위 검증
    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new LottoException(ErrorCode.INVALID_WINNING_NUMBERS_RANGE);
            }
        }
    }

    // 번호 중복 검증
    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw new LottoException(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE);
        }
    }

    // 숫자 리스트 반환
    public List<Integer> getNumbers() {
        return numbers;
    }
}