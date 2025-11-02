package lotto.domain.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.global.error.ErrorCode;
import lotto.global.error.ExceptionFactory;
import lotto.global.message.MessageCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InputView {

    // 로또 구입 금액 입력
    public static int readPurchaseAmount() {
        System.out.println(MessageCode.PURCHASE_AMOUNT_INPUT.getMessage());
        String input = Console.readLine();

        if (input == null || input.trim().isEmpty()) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT_FORMAT);
        }

        return parsePurchaseAmount(input);
    }

    // 구입 금액 파싱
    private static int parsePurchaseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            validatePurchaseAmount(amount);
            return amount;
        } catch (NumberFormatException e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_PURCHASE_AMOUNT_FORMAT);
        }
    }

    // 구입 금액 검증
    private static void validatePurchaseAmount(int amount) {
        if (amount <= 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }

        if (amount % 1000 != 0) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_PURCHASE_AMOUNT);
        }
    }

    // 당첨 번호 입력
    public static List<Integer> readWinningNumbers() {
        System.out.println(MessageCode.WINNING_NUMBERS_INPUT.getMessage());
        String input = Console.readLine();
        return parseWinningNumbers(input);
    }

    // 당첨 번호 파싱
    private static List<Integer> parseWinningNumbers(String input) {
        try {
            validateInputNotEmpty(input);
            String[] numberStrings = input.split(",");
            validateNumberCount(numberStrings);

            List<Integer> numbers = convertToIntList(numberStrings);
            validateNumberRange(numbers);
            validateNoDuplicates(numbers);

            return numbers;
        } catch (NumberFormatException e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_WINNING_NUMBERS_FORMAT);
        }
    }

    // 입력이 비어있는지 확인
    private static void validateInputNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_FORMAT);
        }
    }

    // 번호 개수 검증
    private static void validateNumberCount(String[] numberStrings) {
        if (numberStrings == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        if (numberStrings.length != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_COUNT);
        }
    }

    // 문자열 배열을 정수 리스트로 변환
    private static List<Integer> convertToIntList(String[] numberStrings) {
        if (numberStrings == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        List<Integer> numbers = new ArrayList<>();
        for (String numberStr : numberStrings) {
            if (numberStr == null || numberStr.trim().isEmpty()) {
                throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_FORMAT);
            }
            numbers.add(Integer.parseInt(numberStr.trim()));
        }
        return numbers;
    }

    // 번호 범위 검증
    private static void validateNumberRange(List<Integer> numbers) {
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

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
    private static void validateNoDuplicates(List<Integer> numbers) {
        if (numbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_LOTTO_NUMBERS);
        }

        if (new HashSet<>(numbers).size() != 6) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE);
        }
    }

    // 보너스 번호 입력
    public static int readBonusNumber(List<Integer> winningNumbers) {
        if (winningNumbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        }

        System.out.println(MessageCode.BONUS_NUMBER_INPUT.getMessage());
        String input = Console.readLine();
        return parseBonusNumber(input, winningNumbers);
    }

    // 보너스 번호 파싱
    private static int parseBonusNumber(String input, List<Integer> winningNumbers) {
        if (input == null || input.trim().isEmpty()) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_FORMAT);
        }

        if (winningNumbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        }

        try {
            int bonusNumber = Integer.parseInt(input.trim());
            validateBonusNumberRange(bonusNumber);
            validateBonusNumberNotDuplicate(bonusNumber, winningNumbers);
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw ExceptionFactory.numberFormat(ErrorCode.INVALID_BONUS_NUMBER_FORMAT);
        }
    }

    // 보너스 번호 범위 검증
    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_RANGE);
        }
    }

    // 보너스 번호 중복 검증
    private static void validateBonusNumberNotDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers == null) {
            throw ExceptionFactory.nullPointer(ErrorCode.NULL_WINNING_LOTTO);
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw ExceptionFactory.illegalArgument(ErrorCode.INVALID_BONUS_NUMBER_DUPLICATE);
        }
    }
}