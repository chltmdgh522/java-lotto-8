package lotto.global.error;

public enum ErrorCode {
    INVALID_PURCHASE_AMOUNT("로또 구입 금액은 1,000원 단위여야 합니다."),
    INVALID_PURCHASE_AMOUNT_FORMAT("로또 구입 금액은 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_COUNT("당첨 번호는 6개여야 합니다."),
    INVALID_WINNING_NUMBERS_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_DUPLICATE("당첨 번호에 중복된 숫자가 있습니다."),
    INVALID_WINNING_NUMBERS_FORMAT("당첨 번호는 쉼표(,)로 구분된 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_FORMAT("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}