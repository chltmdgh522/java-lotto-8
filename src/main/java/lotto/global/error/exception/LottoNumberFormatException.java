package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

/**
 * 문자열을 숫자로 변환할 수 없을 때 발생하는 예외
 * 예시: 잘못된 형식의 로또 번호, 구매 금액 등
 */
public class LottoNumberFormatException extends NumberFormatException {
    private final ErrorCode errorCode;

    public LottoNumberFormatException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}