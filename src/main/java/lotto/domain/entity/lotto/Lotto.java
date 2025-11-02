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

    // null 체크 추가
    private void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }
    }

    // 번호 개수 검증
    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_COUNT);
        }
    }

    // 번호 범위 검증
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

    // 번호 중복 검증
    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE);
        }
    }

    // null 요소 검사 추가
    private void validateNoNullElements(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number == null) {
                throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBER);
            }
        }
    }

    // 숫자 리스트 반환
    public List<Integer> getNumbers() {
        return numbers;
    }
}