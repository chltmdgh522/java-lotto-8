package lotto.domain.entity.lotto;

import java.util.HashSet;
import java.util.List;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNotNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        validateNoNullElements(numbers);
        this.numbers = numbers;
    }

    private void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_COUNT);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBER);
            }
            if (number < 1 || number > 45) {
                throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_RANGE);
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE);
        }
    }

    private void validateNoNullElements(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBER);
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
