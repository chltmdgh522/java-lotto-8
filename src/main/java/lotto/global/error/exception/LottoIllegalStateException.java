package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

/**
 * 객체의 상태가 메서드 호출에 적합하지 않을 때 발생하는 예외
 * 예시: 초기화되지 않은 상태에서 메서드 호출, 이미 처리된 로또에 대한 중복 처리 등
 */
public class LottoIllegalStateException extends IllegalStateException {
    private final ErrorCode errorCode;

    public LottoIllegalStateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}