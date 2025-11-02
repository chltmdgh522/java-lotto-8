package lotto.global.error.exception;

import lotto.global.error.ErrorCode;

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