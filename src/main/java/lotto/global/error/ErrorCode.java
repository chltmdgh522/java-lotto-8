package lotto.global.error;

public enum ErrorCode {
    // 기존 에러 코드
    INVALID_PURCHASE_AMOUNT("로또 구입 금액은 1,000원 단위여야 합니다."),
    INVALID_PURCHASE_AMOUNT_FORMAT("로또 구입 금액은 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_COUNT("당첨 번호는 6개여야 합니다."),
    INVALID_WINNING_NUMBERS_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_DUPLICATE("당첨 번호에 중복된 숫자가 있습니다."),
    INVALID_WINNING_NUMBERS_FORMAT("당첨 번호는 쉼표(,)로 구분된 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_FORMAT("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    NULL_LOTTO_NUMBERS("로또 번호 리스트가 null일 수 없습니다."),
    NULL_LOTTO_NUMBER("로또 번호에 null 값이 포함될 수 없습니다."),
    NULL_WINNING_LOTTO("당첨 로또 객체가 null일 수 없습니다."),
    NULL_PURCHASED_LOTTOS("구매한 로또 목록이 null일 수 없습니다."),
    NULL_WINNING_STATISTICS("당첨 통계가 null일 수 없습니다."),

    INVALID_INDEX_ACCESS("유효하지 않은 인덱스에 접근했습니다."),
    EMPTY_COLLECTION_ACCESS("비어있는 컬렉션에 접근했습니다."),

    INVALID_OPERATION_ON_LOTTO("유효하지 않은 로또 조작이 시도되었습니다."),
    CONCURRENT_MODIFICATION("컬렉션 순회 중 수정이 발생했습니다."),

    ARITHMETIC_ERROR("수학적 연산 중 오류가 발생했습니다."),
    DIVIDE_BY_ZERO("0으로 나눌 수 없습니다."),

    UNKNOWN_ERROR("알 수 없는 오류가 발생했습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}