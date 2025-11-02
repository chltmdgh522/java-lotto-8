package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

/**
 * null 참조에 접근하려고 할 때 발생하는 예외
 * 예시: null인 로또 번호 리스트 전달, 메서드에 null 인자 전달 등
 */
public class LottoNullPointerException extends NullPointerException {
    private final ErrorCode errorCode;

    public LottoNullPointerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}