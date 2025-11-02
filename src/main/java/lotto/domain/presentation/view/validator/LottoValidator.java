package lotto.domain.presentation.view.validator;

import java.util.HashSet;
import java.util.List;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;

public class LottoValidator {

    public static void validatePurchaseAmount(int amount) {
        if (amount <= 0 || amount % 1000 != 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
    }

    public static void validateWinningNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6)
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_COUNT);
        if (numbers.stream().anyMatch(n -> n < 1 || n > 45))
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_RANGE);
        if (new HashSet<>(numbers).size() != 6)
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE);
    }

    public static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45)
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_RANGE);
        if (winningNumbers.contains(bonusNumber))
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_DUPLICATE);
    }
}
