package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

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