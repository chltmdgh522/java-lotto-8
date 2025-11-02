package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

/**
 * 메소드에 전달된 인자값이 유효하지 않을 때 발생하는 예외 예시: 범위를 벗어난 로또 번호, 중복된 로또 번호 등
 */
public class LottoIllegalArgumentException extends IllegalArgumentException {
    private final ErrorCode errorCode;

    public LottoIllegalArgumentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}