package lotto.global.message;

public enum MessageCode {
    PURCHASE_AMOUNT_INPUT("구입금액을 입력해 주세요."),
    PURCHASE_LOTTO_COUNT("%d개를 구매했습니다."),
    WINNING_NUMBERS_INPUT("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_INPUT("보너스 번호를 입력해 주세요."),
    WINNING_STATISTICS_TITLE("당첨 통계"),
    WINNING_STATISTICS_FORMAT("%d개 일치%s (%,d원) - %d개"),
    WINNING_STATISTICS_BONUS(", 보너스 볼 일치"),
    PROFIT_RATE_FORMAT("총 수익률은 %.1f%%입니다.");

    private final String message;

    MessageCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}