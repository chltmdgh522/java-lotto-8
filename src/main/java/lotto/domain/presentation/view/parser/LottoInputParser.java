package lotto.domain.presentation.view.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;

public class LottoInputParser {

    public static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_PURCHASE_AMOUNT_FORMAT);
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_WINNING_NUMBERS_FORMAT);
        }
    }

    public static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_BONUS_NUMBER_FORMAT);
        }
    }
}
