package lotto.global.error;

public enum ErrorCode {
    INVALID_CAR_NAME_LENGTH("자동차 이름은 1자 이상 5자 이하만 가능합니다."),
    DUPLICATE_CAR_NAME("중복된 자동차 이름이 존재합니다."),
    EMPTY_CAR_NAME_INPUT("자동차 이름 입력이 비어 있거나 잘못된 구분자입니다."),
    INVALID_ATTEMPT_COUNT("시도 횟수는 1회 이상이어야 합니다."),
    INVALID_ATTEMPT_COUNT_FORMAT("시도 횟수는 숫자로 입력해야 합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
